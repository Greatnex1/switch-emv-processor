package com.ubagroup.switchemv.service;

import com.ubagroup.switchemv.model.FraudContext;
import com.ubagroup.switchemv.model.FraudResult;

public interface FraudRule {
    FraudResult evaluate(FraudContext ctx);
}
