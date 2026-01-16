package com.ubagroup.switchemv.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FraudResult {
    private boolean block;
    private boolean review;
    private String reason;

    public static FraudResult pass() { return FraudResult.builder().block(false).review(false).reason("OK").build(); }
    public static FraudResult block(String reason) { return FraudResult.builder().block(true).review(false).reason(reason).build(); }
    public static FraudResult review(String reason) { return FraudResult.builder().block(false).review(true).reason(reason).build(); }
}
