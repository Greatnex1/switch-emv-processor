package com.ubagroup.switchemv.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue
    private Long userId;

    @Version
    private Long version;

    @Column(nullable = false)
    private BigDecimal balance;

}
