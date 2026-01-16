package com.ubagroup.switchemv.config;

import com.ubagroup.switchemv.dto.EmvPaymentRequest;
import com.ubagroup.switchemv.dto.SwitchResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SwitchClient {

    public SwitchResponse send(EmvPaymentRequest req) {

        // simulate network delay
        try { Thread.sleep(200); } catch (InterruptedException ignored) {}

        return new SwitchResponse(
                true,
                UUID.randomUUID().toString().substring(0,12),
                "00"
        );
    }
}
