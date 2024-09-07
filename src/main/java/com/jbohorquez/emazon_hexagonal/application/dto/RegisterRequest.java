package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Data
@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = PASSWORD,
            message = "Password must be at least 8 characters long, and must include at least one number, one uppercase letter, one lowercase letter, and one special character"
    )
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must have a valid format")
    private String email;

    @NotNull(message = "Identity document is required")
    @Digits(integer = INTEGERS, fraction = DECIMALS, message = "Identity document must be numeric and cannot contain decimals")
    private Long idDocument;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = PHONE_NUMBER, message = "Phone number must be a maximum of 13 characters and may include the '+' symbol")
    private String phone;

    @NotNull(message = "Birthdate is required")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;

    @NotNull(message = "Rol is required")
    private Long rol;
}