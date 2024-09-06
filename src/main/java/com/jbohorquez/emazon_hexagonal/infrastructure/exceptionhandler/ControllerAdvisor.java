package com.jbohorquez.emazon_hexagonal.infrastructure.exceptionhandler;

import com.jbohorquez.emazon_hexagonal.error.ErrorResponse;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NotFoundException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorResponse> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> AlreadyExistsException(
            AlreadyExistsException AlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NOT_EXISTS.getMessage()));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(
            NotFoundException categoryNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.CATEGORY_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INTERNAL_ERROR.getMessage()));
    }
}