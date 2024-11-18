package com.jbohorquez.microservices_users.infrastructure.input.rest;

import com.jbohorquez.microservices_users.application.dto.AuthenticationRequest;
import com.jbohorquez.microservices_users.application.dto.AuthenticationResponse;
import com.jbohorquez.microservices_users.application.handler.IUsersHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AuthenticateControllerTest {

    @InjectMocks
    private AuthenticateController authenticateController;

    @Mock
    private IUsersHandler usersHandler;

    private AuthenticationRequest authenticationRequest;
    private AuthenticationResponse authenticationResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("test@example.com");
        authenticationRequest.setPassword("password123");

        authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken("mocked-jwt-token");
    }

    @Test
    void loginUser_ShouldReturnOkWhenLoginIsSuccessful() {

        when(usersHandler.validateUser(authenticationRequest)).thenReturn(authenticationResponse);

        ResponseEntity<?> response = authenticateController.loginUser(authenticationRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authenticationResponse, response.getBody());
    }
}