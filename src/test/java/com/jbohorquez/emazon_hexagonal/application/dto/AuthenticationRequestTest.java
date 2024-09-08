package com.jbohorquez.emazon_hexagonal.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationRequestTest {

    @Test
    void testBuilder() {
        // Crear un objeto utilizando el builder
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("john@example.com")
                .password("securePassword123")
                .build();

        // Verificar que el email y el password se hayan establecido correctamente
        assertEquals("john@example.com", request.getEmail());
        assertEquals("securePassword123", request.getPassword());
    }

    @Test
    void testNoArgsConstructor() {
        // Crear un objeto utilizando el constructor sin argumentos
        AuthenticationRequest request = new AuthenticationRequest();

        // Inicialmente, el email y el password deberían ser nulos
        assertNull(request.getEmail());
        assertNull(request.getPassword());

        // Establecer valores para email y password
        request.setEmail("jane@example.com");
        request.setPassword("strongPassword456");

        // Verificar que los valores se hayan establecido correctamente
        assertEquals("jane@example.com", request.getEmail());
        assertEquals("strongPassword456", request.getPassword());
    }

    @Test
    void testAllArgsConstructor() {
        // Crear un objeto utilizando el constructor con todos los argumentos
        AuthenticationRequest request = new AuthenticationRequest("john@example.com", "password123");

        // Verificar que el email y el password se hayan establecido correctamente
        assertEquals("john@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());
    }

    @Test
    void testSetterGetter() {
        // Crear un objeto vacío
        AuthenticationRequest request = new AuthenticationRequest();

        // Establecer email y password
        request.setEmail("example@example.com");
        request.setPassword("mySecurePassword");

        // Verificar que los valores se hayan establecido correctamente
        assertEquals("example@example.com", request.getEmail());
        assertEquals("mySecurePassword", request.getPassword());
    }
}