package com.jbohorquez.microservices_users.infrastructure.exceptionhandler;

import com.jbohorquez.microservices_users.error.ErrorResponse;
import com.jbohorquez.microservices_users.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.microservices_users.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.microservices_users.infrastructure.exception.NotFoundException;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.MESSAGE;

@ControllerAdvice
public class ControllerAdvisor {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorResponse> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJwtException(MalformedJwtException ex) {
        logger.error("Malformed JWT Exception: ", ex);
        ErrorResponse errorResponse = new ErrorResponse("JWT Token", "Invalid or malformed JWT token");
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
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
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentialsException(MalformedJwtException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ExceptionResponse.ACCESS_DENIED.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }
}
