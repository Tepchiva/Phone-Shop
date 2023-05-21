package com.chiva.phoneshop.service;

import com.chiva.phoneshop.exception.ErrorResponse;
import com.chiva.phoneshop.exception.SuccessResponse;
import org.springframework.http.ResponseEntity;

public interface MessageResponseService {
    public ResponseEntity<ErrorResponse> handleErrorMsgResponse(String code, String defaultMessage);
    public <T> ResponseEntity<SuccessResponse<T>> handleSuccessMsgResponse(T body);
}
