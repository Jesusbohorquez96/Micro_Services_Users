package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserResponse {

    private Long userId;
    private String userName;
    private String userLastName;
    private Long userIdentityDocument;
    private String userPhone;
    private LocalDate userBirthdate;
    private String userEmail;
    private String userPassword;
}
