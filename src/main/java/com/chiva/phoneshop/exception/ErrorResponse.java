package com.chiva.phoneshop.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private String code;
    private String message;
    private String traceId;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponse(String code, String message, String traceId) {
        this.code = code;
        this.message = message;
        this.traceId = traceId;
    }
}
