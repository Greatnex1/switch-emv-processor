package com.ubagroup.switchemv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubagroup.switchemv.model.Wallet;
import com.ubagroup.switchemv.model.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
//    @JsonProperty("access_token")
//    private String accessToken;
    private String username;
    private String accessToken;
    private String refreshToken;
//   private String role;
    private RoleType role;
    private List<Wallet> wallets;
}
