package com.ubagroup.switchemv.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppUserDto {
    private String username;
    private String password;
    private String role;
}
