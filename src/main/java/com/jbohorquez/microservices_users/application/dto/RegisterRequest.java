package com.jbohorquez.microservices_users.application.dto;

import com.jbohorquez.microservices_users.application.validation.Adult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.*;
import java.time.LocalDate;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@Data
@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = NAME_REQUIRED)
    private String name;

    @NotBlank(message = LAST_NAME_REQUIRED)
    private String lastName;

    @NotBlank(message = PASSWORD_REQUIRED)
    @Pattern(
            regexp = PASSWORD,
            message = PASSWORD_INVALID
    )
    private String password;

    @Column(length = EMAIL_MAX_LENGTH, nullable = false)
    @NotBlank(message = EMAIL_REQUIRED)
    @Email(message = EMAIL_INVALID_FORMAT)
    private String email;

    @NotNull(message = ID_DOCUMENT_REQUIRED)
    @Digits(integer = INTEGERS, fraction = DECIMALS, message = ID_DOCUMENT_NUMERIC)
    private Long idDocument;

    @NotBlank(message = PHONE_REQUIRED)
    @Pattern(regexp = PHONE_NUMBER, message = PHONE_INVALID)
    private String phone;

    @NotNull(message = BIRTHDATE_REQUIRED)
    @Past(message = BIRTHDATE_PAST)
    @Adult(message = USER_MUST_BE_ADULT)
    private LocalDate birthdate;

    @NotNull(message = ROL_REQUIRED)
    @Transient
    private Long rol;

}