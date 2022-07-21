package com.app.tracker.apis.handlers;

import com.app.tracker.core.exceptions.BaseError;
import com.app.tracker.core.exceptions.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler({BaseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseError> handleApiException(BaseException baseException) {
        baseException.printStackTrace();
        log.error(baseException.getMessage());

        return new ResponseEntity<>(baseException.getError(), baseException.getError()
                .getStatus());
    }
}
