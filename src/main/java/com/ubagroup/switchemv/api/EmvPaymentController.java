package com.ubagroup.switchemv.api;

import com.ubagroup.switchemv.dto.EmvPaymentRequest;
import com.ubagroup.switchemv.dto.PaymentResponse;
import com.ubagroup.switchemv.service.EmvPaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/payments")
@RequiredArgsConstructor
public class EmvPaymentController {

    private final EmvPaymentService paymentService;

    @PostMapping("/card")
    public ResponseEntity<PaymentResponse> pay(
            @Valid @RequestBody EmvPaymentRequest request) {

        PaymentResponse response = paymentService.process(request);
        return ResponseEntity.ok(response);
    }
}
