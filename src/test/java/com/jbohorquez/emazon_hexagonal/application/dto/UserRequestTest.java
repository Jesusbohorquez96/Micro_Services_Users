package com.jbohorquez.emazon_hexagonal.application.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenUserRequestIsValid_thenNoViolations() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setName("John");
        userRequest.setLastName("Doe");
        userRequest.setIdentityDocument(123456789L);
        userRequest.setPhone("+1234567890");
        userRequest.setBirthdate(LocalDate.of(1990, 1, 1));
        userRequest.setEmail("john.doe@example.com");
        userRequest.setPassword("Password@123");
        userRequest.setRol(1L);

        // Act
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        // Assert
        assertTrue(violations.isEmpty());
    }

    @Test
    void whenNameIsBlank_thenViolation() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setName(""); // Blank name
        userRequest.setLastName("Doe");
        userRequest.setIdentityDocument(123456789L);
        userRequest.setPhone("+1234567890");
        userRequest.setBirthdate(LocalDate.of(1990, 1, 1));
        userRequest.setEmail("john.doe@example.com");
        userRequest.setPassword("Password@123");
        userRequest.setRol(1L);

        // Act
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        // Assert
        assertEquals(1, violations.size());
        ConstraintViolation<UserRequest> violation = violations.iterator().next();
        assertEquals("Name is required", violation.getMessage());
    }

    @Test
    void whenEmailIsInvalid_thenViolation() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setName("John");
        userRequest.setLastName("Doe");
        userRequest.setIdentityDocument(123456789L);
        userRequest.setPhone("+1234567890");
        userRequest.setBirthdate(LocalDate.of(1990, 1, 1));
        userRequest.setEmail("invalid-email"); // Invalid email
        userRequest.setPassword("Password@123");
        userRequest.setRol(1L);

        // Act
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        // Assert
        assertEquals(1, violations.size());
        ConstraintViolation<UserRequest> violation = violations.iterator().next();
        assertEquals("Email must have a valid format", violation.getMessage());
    }

    @Test
    void whenPasswordIsWeak_thenViolation() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setName("John");
        userRequest.setLastName("Doe");
        userRequest.setIdentityDocument(123456789L);
        userRequest.setPhone("+1234567890");
        userRequest.setBirthdate(LocalDate.of(1990, 1, 1));
        userRequest.setEmail("john.doe@example.com");
        userRequest.setPassword("weak"); // Weak password
        userRequest.setRol(1L);

        // Act
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        // Assert
        assertEquals(1, violations.size());
        ConstraintViolation<UserRequest> violation = violations.iterator().next();
        assertEquals(
                "Password must be at least 8 characters long, and must include at least one number, one uppercase letter, one lowercase letter, and one special character",
                violation.getMessage()
        );
    }

    @Test
    void whenBirthdateIndicatesUnderage_thenViolation() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setName("John");
        userRequest.setLastName("Doe");
        userRequest.setIdentityDocument(123456789L);
        userRequest.setPhone("+1234567890");
        userRequest.setBirthdate(LocalDate.now().minusYears(17)); // Underage
        userRequest.setEmail("john.doe@example.com");
        userRequest.setPassword("Password@123");
        userRequest.setRol(1L);

        // Act
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        // Assert
        assertEquals(1, violations.size());
        ConstraintViolation<UserRequest> violation = violations.iterator().next();
        assertEquals("User must be at least 18 years old", violation.getMessage());
    }
}