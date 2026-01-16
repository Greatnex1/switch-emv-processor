package com.ubagroup.switchemv.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class FraudContext {
    private Long userId;
    private BigDecimal amount;
    private String terminalId;
    private String merchantId;
    private int txCountLastHour;
    private String country;
}