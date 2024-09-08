package com.jbohorquez.emazon_hexagonal.application.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
        // Verificar que una instancia válida de RegisterRequest no genera violaciones
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNameValidation() {
        registerRequest.setName("");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        assertEquals("Name is required", violations.iterator().next().getMessage());
    }

    @Test
    void testLastNameValidation() {
        registerRequest.setLastName("");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        assertEquals("Last name is required", violations.iterator().next().getMessage());
    }

    @Test
    void testPasswordValidation() {
        // Probar el patrón de la contraseña
        registerRequest.setPassword("weakpassword");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        assertEquals(
                "Password must be at least 8 characters long, and must include at least one number, one uppercase letter, one lowercase letter, and one special character",
                violations.iterator().next().getMessage()
        );
    }

    @Test
    void testEmailValidation() {
        // Probar el formato de correo electrónico
        registerRequest.setEmail("invalid-email");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        assertEquals("Email must have a valid format", violations.iterator().next().getMessage());
    }

    @Test
    void testIdDocumentValidation() {
        registerRequest.setIdDocument(null);

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());

        assertTrue(violations.stream()
                .anyMatch(violation -> "Identity document is required".equals(violation.getMessage())));
    }

    @Test
    void testPhoneValidation() {
        // Probar que el teléfono sigue el patrón especificado
        registerRequest.setPhone("123abc");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        assertEquals("Phone number must be a maximum of 13 characters and may include the '+' symbol", violations.iterator().next().getMessage());
    }

    @Test
    void testBirthdateValidation() {
        // Probar que la fecha de nacimiento debe estar en el pasado
        registerRequest.setBirthdate(LocalDate.now().plusDays(1));

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        assertEquals("Birthdate must be in the past", violations.iterator().next().getMessage());
    }

    @Test
    void testRolValidation() {
        // Probar que el rol es obligatorio
        registerRequest.setRol(null);

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        assertEquals("Rol is required", violations.iterator().next().getMessage());
    }
}