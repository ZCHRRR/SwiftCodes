package com.swiftcodes.adapter.in.rest;

import com.swiftcodes.domain.model.SwiftCodeAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(SwiftCodeAlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExists(SwiftCodeAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}