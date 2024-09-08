package com.jbohorquez.emazon_hexagonal.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationResponseTest {

    @Test
    void testBuilder() {
        // Construir un objeto utilizando el builder
        AuthenticationResponse response = AuthenticationResponse.builder()
                .token("sampleToken123")
                .build();

        // Verificar que el token se haya establecido correctamente
        assertEquals("sampleToken123", response.getToken());
    }

    @Test
    void testNoArgsConstructor() {
        // Crear un objeto utilizando el constructor sin argumentos
        AuthenticationResponse response = new AuthenticationResponse();

        // Inicialmente, el token debería ser nulo
        assertNull(response.getToken());

        // Establecer el token y verificar que el valor sea correcto
        response.setToken("anotherToken456");
        assertEquals("anotherToken456", response.getToken());
    }

    @Test
    void testAllArgsConstructor() {
        // Crear un objeto utilizando el constructor con todos los argumentos
        AuthenticationResponse response = new AuthenticationResponse("finalToken789");

        // Verificar que el token se haya establecido correctamente
        assertEquals("finalToken789", response.getToken());
    }

    @Test
    void testSetterGetter() {
        // Crear un objeto vacío
        AuthenticationResponse response = new AuthenticationResponse();

        // Establecer el token
        response.setToken("myToken");

        // Verificar que el token se haya establecido correctamente
        assertEquals("myToken", response.getToken());
    }
}