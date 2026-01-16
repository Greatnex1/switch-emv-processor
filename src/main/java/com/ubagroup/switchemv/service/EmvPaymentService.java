package com.ubagroup.switchemv.service;


import com.ubagroup.switchemv.config.SwitchClient;
import com.ubagroup.switchemv.dto.EmvPaymentRequest;
import com.ubagroup.switchemv.dto.PaymentResponse;
import com.ubagroup.switchemv.dto.SwitchResponse;
import com.ubagroup.switchemv.model.FraudContext;
import com.ubagroup.switchemv.model.FraudResult;
import com.ubagroup.switchemv.model.Payment;
import com.ubagroup.switchemv.model.enums.PaymentStatus;
import com.ubagroup.switchemv.repository.PaymentRepository;
import com.ubagroup.switchemv.rule.FraudEngine;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmvPaymentService {

    private final PaymentRepository paymentRepo;
    private final WalletFundingService walletService;
    private final FraudEngine fraudEngine;
    private final SwitchClient switchClient;
    private final AsyncNotificationService notificationService;

    @Transactional
    public PaymentResponse process(EmvPaymentRequest req) {

        // Idempotency
        if (paymentRepo.existsByReference(req.getReference())) {
            return paymentRepo.findByReference(req.getReference())
                    .map(PaymentResponse::from)
                    .orElseThrow();
        }

        // Fraud check
        FraudContext ctx = FraudContext.builder()
                .userId(req.getUserId())
                .amount(req.getAmount())
                .terminalId(req.getTerminalId())
                .merchantId(req.getMerchantId())
                .txCountLastHour(0)
                .country(req.getCountry())
                .build();

        FraudResult fraudResult = fraudEngine.evaluate(ctx);
        if (fraudResult.isBlock()) {
            return PaymentResponse.builder()
                    .status(PaymentStatus.FAILED)
                    .reason("FRAUD_BLOCK: " + fraudResult.getReason())
                    .build();
        }

        // Wallet debit (DB locked)
        walletService.debit(req.getUserId(), req.getAmount());

        Payment p = Payment.builder()
                .reference(req.getReference())
                .amount(req.getAmount())
                .status(PaymentStatus.PENDING)
                .build();

        paymentRepo.save(p);

        // Switch call
        SwitchResponse res = switchClient.send(req);

        p.setRrn(res.getRrn());
        p.setAuthCode(res.getAuthCode());
        p.setStatus(res.isApproved() ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);

        // Async
        if (fraudResult.isReview()) notificationService.sendForReview(p);
        notificationService.sendReceipt(p);

        return PaymentResponse.from(p);
    }

}