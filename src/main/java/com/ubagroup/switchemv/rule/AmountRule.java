package com.ubagroup.switchemv.rule;

import com.ubagroup.switchemv.model.FraudContext;
import com.ubagroup.switchemv.model.FraudResult;
import com.ubagroup.switchemv.service.FraudRule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AmountRule implements FraudRule {
    @Override
    public FraudResult evaluate(FraudContext ctx) {
        if (ctx.getAmount().compareTo(new BigDecimal("500000")) > 0)
            return FraudResult.review("High value transaction");
        return FraudResult.pass();
    }
}