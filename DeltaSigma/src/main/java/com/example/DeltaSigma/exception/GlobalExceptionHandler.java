package com.example.DeltaSigma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation errors
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<Map<String, Object>> handleValidation(Exception ex) {

        Map<String, Object> error = new HashMap<>();
        Map<String, String> fieldErrors = new HashMap<>();

        if (ex instanceof MethodArgumentNotValidException e) {
            e.getBindingResult().getFieldErrors()
                    .forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));
        }

        if (ex instanceof BindException e) {
            e.getBindingResult().getFieldErrors()
                    .forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));
        }

        error.put("timestamp", LocalDateTime.now());
        error.put("status", 400);
        error.put("error", "Validation Failed");
        error.put("messages", fieldErrors);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Duplicate ID error
    @ExceptionHandler(DuplicateIdException.class)
    public ResponseEntity<Map<String,Object>> handleDuplicateId(DuplicateIdException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", 400);
        error.put("error", "Duplicate ID");
        error.put("message", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}