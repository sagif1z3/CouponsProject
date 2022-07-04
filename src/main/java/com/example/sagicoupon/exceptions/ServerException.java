package com.example.sagicoupon.exceptions;

import com.example.sagicoupon.enums.ErrorType;

public class ServerException extends Exception {
    private ErrorType errorType;

    public ServerException(ErrorType errorType) {
        super(errorType.getterOfMessage() + ": " + errorType.getErrorNumber());
        this.errorType = errorType;
        if (errorType.isShowStackTrace()) {
            printStackTrace();
        }
    }

    public ServerException(ErrorType errorType, String message) {
        super(message + ": " + errorType.getterOfMessage() + ": " + errorType.getErrorNumber());
        this.errorType = errorType;
        if (errorType.isShowStackTrace()) {
            printStackTrace();
        }
    }

    public ServerException(Exception e, ErrorType errorType) {
        super(e.getMessage() + ": " + errorType.getterOfMessage() + ": " + errorType.getErrorNumber(), e);
        this.errorType = errorType;
        if (errorType.isShowStackTrace()) {
            printStackTrace();
        }
    }

    public ServerException(Exception e, ErrorType errorType, String message) {
        super(e.getMessage() + ": " + message + ": " + errorType.getterOfMessage() + ": " + errorType.getErrorNumber(), e);
        this.errorType = errorType;
        if (errorType.isShowStackTrace()) {
            printStackTrace();
        }
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
