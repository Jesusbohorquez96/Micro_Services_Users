package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.AuthenticationRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.AuthenticationResponse;
import com.jbohorquez.emazon_hexagonal.application.dto.RegisterRequest;
import com.jbohorquez.emazon_hexagonal.application.handler.IUsersHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AutenticateControllerTest {

    @Mock
    private IUsersHandler usersHandler;

    @InjectMocks
    private AutenticateController autenticateController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginUserSuccess() {
        // Arrange
        AuthenticationRequest authRequest = new AuthenticationRequest("user@example.com", "password123");
        AuthenticationResponse authResponse = new AuthenticationResponse("fake-jwt-token");
        when(usersHandler.validateUser(authRequest)).thenReturn(authResponse);

        // Act
        ResponseEntity<AuthenticationResponse> response = autenticateController.loginUser(authRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("fake-jwt-token", response.getBody().getToken());
        verify(usersHandler, times(1)).validateUser(authRequest);
    }

    @Test
    public void testRegisterUserSuccess() {
        // Arrange
        RegisterRequest registerRequest = new RegisterRequest();
        AuthenticationResponse authResponse = new AuthenticationResponse("fake-jwt-token");
        when(usersHandler.registerUser(registerRequest)).thenReturn(authResponse);

        // Act
        ResponseEntity<AuthenticationResponse> response = autenticateController.register(registerRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("fake-jwt-token", response.getBody().getToken());
        verify(usersHandler, times(1)).registerUser(registerRequest);
    }

    @Test
    public void testRegisterUserUnauthorized() {
        // Arrange
        RegisterRequest registerRequest = new RegisterRequest();

        when(usersHandler.registerUser(registerRequest)).thenThrow(new SecurityException("Unauthorized"));

        // Act & Assert
        SecurityException exception = assertThrows(SecurityException.class, () -> {
            autenticateController.register(registerRequest);
        });

        assertEquals("Unauthorized", exception.getMessage());
        verify(usersHandler, times(1)).registerUser(registerRequest);
    }
}