package com.ubagroup.switchemv.model;

import com.ubagroup.switchemv.model.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name = "wallet_id")
    private Wallet wallet;

}
