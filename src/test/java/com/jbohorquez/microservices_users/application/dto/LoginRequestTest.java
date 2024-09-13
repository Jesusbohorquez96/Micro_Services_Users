package com.jbohorquez.microservices_users.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void testSetterGetter() {
        // Crear un objeto LoginRequest
        LoginRequest request = new LoginRequest();

        // Establecer email y password
        request.setEmail("user@example.com");
        request.setPassword("securePassword123");

        // Verificar que los valores se hayan establecido correctamente
        assertEquals("user@example.com", request.getEmail());
        assertEquals("securePassword123", request.getPassword());
    }

    @Test
    void testDefaultValues() {
        // Crear un objeto LoginRequest sin establecer valores
        LoginRequest request = new LoginRequest();

        // Verificar que el email y el password inicialmente sean null
        assertNull(request.getEmail());
        assertNull(request.getPassword());
    }

    @Test
    void testSetEmail() {
        // Crear un objeto LoginRequest
        LoginRequest request = new LoginRequest();

        // Establecer el email
        request.setEmail("test@example.com");

        // Verificar que el email se haya establecido correctamente
        assertEquals("test@example.com", request.getEmail());
    }

    @Test
    void testSetPassword() {
        // Crear un objeto LoginRequest
        LoginRequest request = new LoginRequest();

        // Establecer el password
        request.setPassword("password123");

        // Verificar que el password se haya establecido correctamente
        assertEquals("password123", request.getPassword());
    }
}