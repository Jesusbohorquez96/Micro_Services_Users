package com.jbohorquez.microservices_users.application.dto;

import com.jbohorquez.microservices_users.application.validation.Adult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@Data
@Getter
@Setter
public class UserRequest {

    @NotBlank(message = NAME_REQUIRED)
    private String name;

    @NotBlank(message = LAST_NAME_REQUIRED)
    private String lastName;

    @NotNull(message = ID_DOCUMENT_REQUIRED)
    @Digits(integer = MAX_DOCUMENT, fraction = ZERO, message = ID_DOCUMENT_NUMERIC)
    private Long identityDocument;

    @NotBlank(message = PHONE_REQUIRED)
    @Pattern(regexp = PHONE_NUMBER, message = PHONE_INVALID)
    private String phone;

    @Adult(message = USER_MUST_BE_ADULT)
    @NotNull(message = BIRTHDATE_REQUIRED)
    private LocalDate birthdate;

    @NotBlank(message = EMAIL_REQUIRED)
    @Email(message = EMAIL_INVALID_FORMAT)
    private String email;

    @NotBlank(message = PASSWORD_REQUIRED)
    @Pattern(
            regexp = PASSWORD,
            message = PASSWORD_INVALID
    )
    private String password;

    @NotNull(message = ROL_REQUIRED)
    private Long rol;
}
