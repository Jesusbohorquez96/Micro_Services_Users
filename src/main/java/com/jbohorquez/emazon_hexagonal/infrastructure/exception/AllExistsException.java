package com.jbohorquez.emazon_hexagonal.infrastructure.exception;

public class AllExistsException extends RuntimeException {
    public AllExistsException(String massage) {
        super(massage);
    }
}
