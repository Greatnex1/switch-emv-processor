package com.ubagroup.switchemv.rule;

import com.ubagroup.switchemv.model.FraudContext;
import com.ubagroup.switchemv.model.FraudResult;
import com.ubagroup.switchemv.service.FraudRule;
import com.ubagroup.switchemv.service.VelocityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VelocityRule implements FraudRule {

    private final VelocityService velocityService;

    @Override
    public FraudResult evaluate(FraudContext ctx) {

        int count = velocityService.incrementAndGet(ctx.getUserId());

        if (count > 5) {
            return FraudResult.block("Too many transactions in short time");
        }

        return FraudResult.pass();
    }
}
