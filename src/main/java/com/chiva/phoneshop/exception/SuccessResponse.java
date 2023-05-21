package com.chiva.phoneshop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse<T> {
    private String code;
    private String message;
    private String traceId;
    private T response;
}
