package com.workintech.s18d4.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class CustomerException extends RuntimeException {
    private HttpStatus status;

    public CustomerException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
