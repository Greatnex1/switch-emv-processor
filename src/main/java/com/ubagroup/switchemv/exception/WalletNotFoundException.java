package com.ubagroup.switchemv.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public class WalletNotFoundException extends RuntimeException {

    private Long userId;

    public WalletNotFoundException(String message) {
        super(message);
    }

    public WalletNotFoundException(String message, Long userId) {
    super(message);
    this.userId = userId;



    }

}
