package com.ubagroup.switchemv.service;

import com.ubagroup.switchemv.model.Payment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncNotificationService {

    @Async
    public void sendReceipt(Payment p) { /* send email/SMS */ }

    @Async
    public void sendForReview(Payment p) { /* queue for manual review */ }
}