package com.chiva.phoneshop.aspect;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LoggingAop {

    private final Tracer tracer;

    @Around("execution(* com.chiva.phoneshop..*.*(..)) && !execution(* com.chiva.phoneshop.config..*(..))")
    public Object logAroundAnyMethods(ProceedingJoinPoint joinPoint) throws Throwable{
        Span span = tracer.activeSpan();
        if (span != null) {
            MDC.put("TRACE_ID", tracer.activeSpan().context().toSpanId());

            this.logRequest(joinPoint);
            // Execute method
            Object result = joinPoint.proceed();

            this.logResponse(joinPoint, result);

            return result;
        }
        else {
            return joinPoint.proceed();
        }
    }

    private void logRequest(ProceedingJoinPoint joinPoint) {
        String[] parameterNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Map<String, String> requestData = new HashMap<>();

        /// Loop through each params and extract its name and value
        for (int i = 0; i < parameterNames.length; i++) {
            try {
                String paramName = parameterNames[i];
                if (paramName.equals("codeSignature") || paramName.equals("context") || paramName.equals("typeReference")) {
                    continue;
                }

                String paramValue = mapper.writeValueAsString(joinPoint.getArgs()[i]);
                requestData.put(paramName, paramValue);
            } catch (JsonProcessingException e) {
                log.error("Jackson failed converting data to JSON String: " + e.getMessage());
            }
        }

        /// Prepare data into "request"
        Map<String, Object> logData = new HashMap<>();
        logData.put("action", "Enter");
        logData.put("requestData", requestData);
        logData.put("method", joinPoint.getSignature().toShortString());
        logData.put("createdAt", LocalDateTime.now().toString());


        /// Log info before execute method
        try {
            String jsonData = mapper.writeValueAsString(logData);
            log.info(jsonData);
        } catch (JsonProcessingException e) {
            log.error("Jackson failed converting data to JSON String: " + e.getMessage());
        }
    }

    private void logResponse(ProceedingJoinPoint joinPoint, Object result) {
        /// Log info after execute method
        Map<String, Object> logData = new HashMap<>();
        logData.put("action", "Exit");
        logData.put("responseData", result);
        logData.put("method", joinPoint.getSignature().toShortString());
        logData.put("createdAt", LocalDateTime.now().toString());

        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            String jsonData = mapper.writeValueAsString(logData);
            log.info(jsonData);
        } catch (JsonProcessingException e) {
            log.error("Jackson failed converting data to JSON String: " + e.getMessage());
        }
    }
}
