package com.jbohorquez.microservices_users.infrastructure.exception;

public class DescriptionTooLongException extends RuntimeException {
    public DescriptionTooLongException(String descriptionIsTooLong) {
        super();
    }
}
