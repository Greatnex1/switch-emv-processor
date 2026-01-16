package com.ubagroup.switchemv.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmvPaymentRequest {
    private String reference;
    private Long userId;
    private String terminalId;
    private String merchantId;
    @NotNull
    @DecimalMin(value = "0.01", inclusive = true)
    private BigDecimal amount;
    private String panToken;
    private String emvTlvData;
    private String country;
}