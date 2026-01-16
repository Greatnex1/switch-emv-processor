package com.ubagroup.switchemv.dto;

import com.ubagroup.switchemv.model.Payment;
import com.ubagroup.switchemv.model.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentResponse {

    private String reference;
    private BigDecimal amount;
    private PaymentStatus status;
    private String rrn;
    private String authCode;
    private String reason;

    public static PaymentResponse from(Payment p) {
        return PaymentResponse.builder()
                .reference(p.getReference())
                .amount(p.getAmount())
                .status(p.getStatus())
                .rrn(p.getRrn())
                .authCode(p.getAuthCode())
                .build();
    }
}
