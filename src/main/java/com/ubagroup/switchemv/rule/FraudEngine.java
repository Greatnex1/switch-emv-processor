package com.ubagroup.switchemv.rule;

import com.ubagroup.switchemv.model.FraudContext;
import com.ubagroup.switchemv.model.FraudResult;
import com.ubagroup.switchemv.service.FraudRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FraudEngine {
    private final List<FraudRule> rules;

    public FraudResult evaluate(FraudContext ctx) {
        for (FraudRule rule : rules) {
            FraudResult result = rule.evaluate(ctx);
            if (result.isBlock()) return result;
            if (result.isReview()) return result;
        }
        return FraudResult.pass();
    }
}