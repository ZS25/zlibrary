package com.sooruth.zlibrary.exception;

import java.util.UUID;

public class BusinessException extends RuntimeException {
    private String errorCode;

    public BusinessException(String message) {
        super(message);
        this.errorCode = UUID.randomUUID().toString();
    }

    public String getErrorCode() {
        return errorCode;
    }
}
