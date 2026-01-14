package com.ubagroup.switchemv.repository;

import com.ubagroup.switchemv.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    boolean existsByReference(String reference);

    Optional<Payment> findByReference(String reference);
}