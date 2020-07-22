package com.fieldbooking.server.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;

public class ErrorMessage {

    private final HashMap<String, String> errors = new HashMap<>();

    public ErrorMessage(BindingResult bindingResult) {
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void addError(String field, String defaultMessage) {
        errors.put(field, defaultMessage);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public ResponseEntity<Object> getResponseEntity() {
        return new ResponseEntity<>(errors, HttpStatus.MULTI_STATUS);
    }
}
