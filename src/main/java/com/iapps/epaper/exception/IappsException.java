package com.iapps.epaper.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class with attribute code and error message
 * This exception will be handled by ControllerAdvice and response client with meaningful message
 */
@ResponseStatus (value = HttpStatus.NOT_FOUND)
@Getter
public class IappsException extends RuntimeException {

    private int code;
    private String message;

    public IappsException(int code, String message) {
        super(code + " " + message);
        this.code = code;
        this.message = message;
    }
}
