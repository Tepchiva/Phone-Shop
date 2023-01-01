package com.chiva.phoneshop.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResourceNotFoundException extends ApiException {

    private Integer resourceId;
    private String resourceName;
    public ResourceNotFoundException(String resourceName, Integer resourceId) {
        super(HttpStatus.NOT_FOUND,"ERR-009", "%s not found for id: %d".formatted(resourceName, resourceId));
    }
}
