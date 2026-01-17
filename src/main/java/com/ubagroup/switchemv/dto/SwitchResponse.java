package com.ubagroup.switchemv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwitchResponse {
    private boolean approved;
    private String rrn;
    private String authCode;

}

