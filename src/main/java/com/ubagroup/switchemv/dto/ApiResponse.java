package com.ubagroup.switchemv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private String message;

//    private Long id;

    @JsonProperty(value = "data")
    private T data;


    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>( message, null);
    }
    public static <T> ApiResponse success(String message) {
        return new ApiResponse<>( message, null);
    }
}
