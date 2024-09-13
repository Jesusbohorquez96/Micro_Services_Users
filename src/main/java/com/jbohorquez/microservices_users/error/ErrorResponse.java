package com.jbohorquez.microservices_users.error;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {

    private String field;
    private String error;

    public ErrorResponse(String field, String error) {
        this.field = field;
        this.error = error;
    }
}
