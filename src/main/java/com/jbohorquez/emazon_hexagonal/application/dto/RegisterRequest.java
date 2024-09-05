package com.jbohorquez.emazon_hexagonal.application.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private Long id;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String role;
    private String idDocument;
}