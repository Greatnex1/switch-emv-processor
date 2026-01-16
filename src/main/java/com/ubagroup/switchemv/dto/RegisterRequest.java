package com.ubagroup.switchemv.dto;

import com.ubagroup.switchemv.model.enums.RoleType;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String role; // MERCHANT or ADMIN
}