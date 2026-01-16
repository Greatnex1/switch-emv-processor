package com.ubagroup.switchemv.util;

import com.ubagroup.switchemv.dto.ApiResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Getter
public final  class ApiResponseHelper {

    private ApiResponseHelper() {
        // prevent instantiation
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(
            String message,
            T data
    ) {
        return ResponseEntity.ok(
                ApiResponse.<T>builder()
//                        .success(true)
                        .message(message)
                        .data(data)
                        .build()
        );
    }

//    public static <T> ResponseEntity<ApiResponse<T>> created(
//            String message,
//            T data
//    ) {
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(
//                        ApiResponse.<T>builder()
////                                .success(true)
//                                .message(message)
//                                .data(data)
//                                .build()
//                );
//    }
}