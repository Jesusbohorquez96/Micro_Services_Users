package com.jbohorquez.microservices_users.domain.usecase;

import com.jbohorquez.microservices_users.application.dto.AuthenticationRequest;
import com.jbohorquez.microservices_users.application.dto.AuthenticationResponse;
import com.jbohorquez.microservices_users.application.dto.RegisterRequest;
import com.jbohorquez.microservices_users.domain.model.User;
import com.jbohorquez.microservices_users.domain.spi.UserPersistencePort;
import com.jbohorquez.microservices_users.infrastructure.adapters.securityconfig.IAuthenticationService;
import com.jbohorquez.microservices_users.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.microservices_users.infrastructure.exception.NameTooLongException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private IAuthenticationService authenticationService;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userUseCase = new UserUseCase(userPersistencePort, passwordEncoder, authenticationService) {

        };
    }

    @Test
    void saveInUser_whenUserExists_shouldThrowAlreadyExistsException() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        when(userPersistencePort.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertThrows(AlreadyExistsException.class, () -> userUseCase.saveInUser(user));
        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void saveInUser_whenNameTooLong_shouldThrowNameTooLongException() {

        User user = new User();
        user.setEmail("test@example.com");
        user.setName("A very very very long name that exceeds the limit");
        when(userPersistencePort.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        assertThrows(NameTooLongException.class, () -> userUseCase.saveInUser(user));
        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void saveInUser_whenValidUser_shouldSaveUserWithEncryptedPassword() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        user.setName("John Doe");
        user.setPassword("plainPassword");

        when(userPersistencePort.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encryptedPassword");

        userUseCase.saveInUser(user);

        assertEquals("encryptedPassword", user.getPassword());
        verify(userPersistencePort).saveUser(user);
    }

    @Test
    void validateUser_shouldReturnAuthenticationResponse() {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest("test@example.com", "password");
        AuthenticationResponse expectedResponse = new AuthenticationResponse("token");

        when(authenticationService.authenticate(authenticationRequest)).thenReturn(expectedResponse);

        AuthenticationResponse response = userUseCase.validateUser(authenticationRequest);

        assertEquals(expectedResponse, response);
    }

    @Test
    void registerUser_shouldCallAuthenticationService() {

        RegisterRequest registerRequest = new RegisterRequest();

        userUseCase.registerUser(registerRequest);

        verify(authenticationService).register(registerRequest);
    }
}