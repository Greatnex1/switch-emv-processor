package com.ubagroup.switchemv.dto;

import lombok.Builder;

import java.time.Instant;
@Builder
public class ApiErrorResponse {
    private String message;
    private Instant timestamp;
}
