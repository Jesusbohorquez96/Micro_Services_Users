package com.jbohorquez.microservices_users.application.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

class RegisterRequestTest {

    private Validator validator;
    private RegisterRequest registerRequest;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        registerRequest = new RegisterRequest();
        registerRequest.setName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setPassword("Passw0rd!");
        registerRequest.setEmail("john@example.com");
        registerRequest.setIdDocument(123456789L);
        registerRequest.setPhone("+123456789");
        registerRequest.setBirthdate(LocalDate.of(1990, 1, 1));
        registerRequest.setRol(1L);
    }

    @Test
    void testValidRegisterRequest() {
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNameValidation() {
        registerRequest.setName("");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        assertEquals(NAME_REQUIRED, violations.iterator().next().getMessage());
    }

    @Test
    void testLastNameValidation() {
        registerRequest.setLastName("");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        assertEquals(LAST_NAME_REQUIRED, violations.iterator().next().getMessage());
    }

    @Test
    void testPasswordValidation() {
        registerRequest.setPassword("weakpassword");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        assertEquals(PASSWORD_INVALID, violations.iterator().next().getMessage());
    }

    @Test
    void testEmailValidation() {
        registerRequest.setEmail("invalid-email");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        assertEquals(EMAIL_INVALID_FORMAT, violations.iterator().next().getMessage());
    }

    @Test
    void testIdDocumentValidation() {
        registerRequest.setIdDocument(null);

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());

        assertTrue(violations.stream()
                .anyMatch(violation -> ID_DOCUMENT_REQUIRED.equals(violation.getMessage())));
    }

    @Test
    void testPhoneValidation() {
        registerRequest.setPhone("123abc");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        assertEquals(PHONE_INVALID, violations.iterator().next().getMessage());
    }

    @Test
    void testBirthdateAdultValidation() {
        registerRequest.setBirthdate(LocalDate.now().minusYears(10));

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(violation -> USER_MUST_BE_ADULT.equals(violation.getMessage())));
    }
}