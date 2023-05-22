package com.chiva.phoneshop.exception;

import com.chiva.phoneshop.constants.MessageResponseCode;
import com.chiva.phoneshop.service.MessageResponseService;
import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final Tracer tracer;
    private final MessageResponseService messageResponseService;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        ErrorResponse errorResponse =  new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException e) {
        log.error("Custom log error: ", e);
        return messageResponseService.handleErrorMsgResponse(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleExceptionError(Throwable throwable) {
        ErrorResponse errorResponse = new ErrorResponse(MessageResponseCode.ERR_500, "Internal server error", tracer.activeSpan().context().toSpanId());
        log.error("THROWABLE EXCEPTION: ", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
