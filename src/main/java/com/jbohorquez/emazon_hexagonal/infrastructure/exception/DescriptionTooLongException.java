package com.jbohorquez.emazon_hexagonal.infrastructure.exception;

public class DescriptionTooLongException extends RuntimeException {
    public DescriptionTooLongException(String descriptionIsTooLong) {
        super();
    }
}
