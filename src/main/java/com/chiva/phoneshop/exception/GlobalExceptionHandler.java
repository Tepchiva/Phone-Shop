package com.chiva.phoneshop.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleHttpClientErrorException(ApiException e) {

        //@TODO: customize response dto message
        return ResponseEntity.status(e.getStatus()).body(e);
    }
}
