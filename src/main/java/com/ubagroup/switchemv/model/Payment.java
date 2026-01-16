package com.ubagroup.switchemv.model;

import com.ubagroup.switchemv.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "reference"))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    private String reference;
    private BigDecimal amount;
    private String rrn;
    private String authCode;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}