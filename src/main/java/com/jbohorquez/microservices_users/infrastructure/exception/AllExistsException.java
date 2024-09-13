package com.jbohorquez.microservices_users.infrastructure.exception;

public class AllExistsException extends RuntimeException {
    public AllExistsException(String massage) {
        super(massage);
    }
}
