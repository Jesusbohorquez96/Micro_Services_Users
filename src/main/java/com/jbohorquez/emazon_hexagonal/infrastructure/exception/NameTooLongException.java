package com.jbohorquez.emazon_hexagonal.infrastructure.exception;

public class NameTooLongException extends RuntimeException {
    public NameTooLongException(String nameIsTooLong) {
        super();
    }
}
