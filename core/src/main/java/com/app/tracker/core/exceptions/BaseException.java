package com.app.tracker.core.exceptions;

public class BaseException extends RuntimeException {

    private final BaseError error;

    public BaseException(BaseError error) {
        super(error.getMessage());
        this.error = error;
    }

    public BaseError getError() {
        return error;
    }
}
