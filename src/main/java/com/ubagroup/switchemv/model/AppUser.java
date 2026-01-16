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
//    @ManyToOne
//    @JoinColumn(name = "role_id")
//    private Role role;


        @Enumerated(EnumType.STRING) @Column(nullable = false)
    private RoleType role;
    @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name = "wallet_id")
    private Wallet wallet;

//    private boolean isEnabled;

}
