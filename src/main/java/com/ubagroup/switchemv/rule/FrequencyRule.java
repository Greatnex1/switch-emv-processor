package com.ubagroup.switchemv.rule;


import com.ubagroup.switchemv.model.FraudContext;
import com.ubagroup.switchemv.model.FraudResult;
import com.ubagroup.switchemv.service.FraudRule;
import org.springframework.stereotype.Component;

@Component
public class FrequencyRule implements FraudRule {
    @Override
    public FraudResult evaluate(FraudContext ctx) {
        if (ctx.getTxCountLastHour() > 5) return FraudResult.block("Too many transactions");
        return FraudResult.pass();
    }
}