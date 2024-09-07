package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    private UserEntity userEntity;
    private Validator validator;
    private RolEntity rolEntity;

    @BeforeEach
    public void setUp() {
        rolEntity = new RolEntity(1L, "ROLE_USER", "User role description");

        userEntity = UserEntity.builder()
                .id(1L)
                .name("John")
                .lastName("Doe")
                .identityDocument(123456789L)
                .phone("+123456789")
                .birthdate(LocalDate.of(1990, 1, 1))
                .email("john.doe@example.com")
                .password("password123")
                .rol(rolEntity)
                .build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidUserEntity() {
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        assertTrue(violations.isEmpty(), "Expected no constraint violations for valid user entity");
    }

    @Test
    public void testInvalidPhoneNumber() {
        userEntity.setPhone("InvalidPhone");
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Phone number must be a maximum of MAX_PHONE characters")));
    }

    @Test
    public void testUserMustBeAdult() {
        userEntity.setBirthdate(LocalDate.now().minusYears(17)); // Less than 18 years old
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("User must be at least 18 years old")));
    }

    @Test
    public void testMissingRequiredFields() {
        userEntity.setName(null);
        userEntity.setEmail(null);
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Name is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Email is required")));
    }
}