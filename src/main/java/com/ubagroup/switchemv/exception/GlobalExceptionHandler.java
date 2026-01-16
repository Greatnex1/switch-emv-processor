package com.ubagroup.switchemv.exception;

import com.ubagroup.switchemv.dto.ApiErrorResponse;
import com.ubagroup.switchemv.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(InsufficientBalanceException.class)
//    public ResponseEntity<ApiErrorResponse> handleBalance(
//            InsufficientBalanceException ex, HttpServletRequest req) {
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(error("INSUFFICIENT_FUNDS", ex.getMessage(), req));
//    }
//
//    @ExceptionHandler(FraudBlockedException.class)
//    public ResponseEntity<ApiErrorResponse> handleFraud(
//            FraudBlockedException ex, HttpServletRequest req) {
//
//        return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                .body(error("FRAUD_BLOCKED", ex.getMessage(), req));
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneral(
            Exception ex, HttpServletRequest req) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error("SYSTEM_ERROR", "Unexpected error occurred", req));
    }

    private ApiErrorResponse error(String code, String msg, HttpServletRequest req) {
        return ApiErrorResponse.builder()
//                .code(code)
                .message(msg)
//                .path(req.getRequestURI())
                .timestamp(Instant.now())
                .build();
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormat(NumberFormatException ex) {
        return ResponseEntity.badRequest().body("Invalid numeric format");
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleWalletNotFound(WalletNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Wallet not found for userId: " + ex.getUserId()));
    }
}

