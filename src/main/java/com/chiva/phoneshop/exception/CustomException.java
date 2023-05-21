package com.chiva.phoneshop.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class CustomException extends RuntimeException{
    String code;

    public CustomException(String code) {
        this.code = code;
    }

    public CustomException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(String code, Exception e) {
        super(e);
        this.code = code;
    }
}
