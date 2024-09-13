package com.jbohorquez.microservices_users.infrastructure.exception;

public class NameTooLongException extends RuntimeException {
    public NameTooLongException(String nameIsTooLong) {
        super();
    }
}
