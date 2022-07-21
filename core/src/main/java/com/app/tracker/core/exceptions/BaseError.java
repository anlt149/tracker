package com.app.tracker.core.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseError {
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;

    public BaseError(HttpStatus status) {
        this(status, "Something went wrong!!!");
    }

    public BaseError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
