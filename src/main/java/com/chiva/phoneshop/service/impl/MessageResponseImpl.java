package com.chiva.phoneshop.service.impl;

import com.chiva.phoneshop.constants.MessageResponseCode;
import com.chiva.phoneshop.exception.ErrorResponse;
import com.chiva.phoneshop.exception.SuccessResponse;
import com.chiva.phoneshop.model.ro.MessageResponseRo;
import com.chiva.phoneshop.repository.ro.MessageResponseRepository;
import com.chiva.phoneshop.service.MessageResponseService;
import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageResponseImpl implements MessageResponseService {
    private final MessageResponseRepository messageResponseRepository;
    private final Tracer tracer;
    @Override
    public ResponseEntity<ErrorResponse> handleErrorMsgResponse(String code, String defaultMessage) {

        if (defaultMessage == null) defaultMessage = "";
        String finalDefaultMessage = defaultMessage;
        String finalDefaultMessage1 = defaultMessage;
        MessageResponseRo messageResponseRo = messageResponseRepository
                .findByCode(code)
                .orElseGet(() -> {
                    MessageResponseRo errorMessage = new MessageResponseRo();
                    errorMessage.setCode(code);
                    errorMessage.setMessage(finalDefaultMessage1.isBlank() ? finalDefaultMessage : "Unexpected error!");
                    errorMessage.setHttpStatus(400);
                    return errorMessage;
                });

        return ResponseEntity
                .status(messageResponseRo.getHttpStatus())
                .body(new ErrorResponse(messageResponseRo.getCode(), messageResponseRo.getMessage(), tracer.activeSpan().context().toSpanId()));
    }

    @Override
    public <T> ResponseEntity<SuccessResponse<T>> handleSuccessMsgResponse(T body) {
        MessageResponseRo messageResponseRo = messageResponseRepository
                .findByCode(MessageResponseCode.SUC_000)
                .orElseGet(() -> {
                    MessageResponseRo successMessage = new MessageResponseRo();
                    successMessage.setCode(MessageResponseCode.SUC_000);
                    successMessage.setMessage("Default successful.");
                    successMessage.setHttpStatus(200);
                    return successMessage;
                });

        return ResponseEntity
                .status(messageResponseRo.getHttpStatus())
                .body(new SuccessResponse<>(messageResponseRo.getCode(), messageResponseRo.getMessage(), tracer.activeSpan().context().toSpanId(), body));
    }
}
