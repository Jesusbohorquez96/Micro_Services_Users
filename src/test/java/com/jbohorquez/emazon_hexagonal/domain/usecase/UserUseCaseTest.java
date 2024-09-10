package com.jbohorquez.emazon_hexagonal.domain.usecase;

import com.jbohorquez.emazon_hexagonal.domain.model.User;
import com.jbohorquez.emazon_hexagonal.domain.spi.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private PasswordEncoder passwordEncoder;


    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userUseCase = new UserUseCase(userPersistencePort, passwordEncoder) {};
    }

    private User user(Long id, String name, String lastName, String email, String password) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    @Test
    void testGetAllUser() {
        // Arrange
        List<User> users = Arrays.asList(
                new User(),
                new User()
        );
        when(userPersistencePort.getAllUser()).thenReturn(users);

        // Act
        List<User> result = userUseCase.getAllUser();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetUserById() {
        // Arrange
        Long userId = 1L;
        User expectedUser = new User();
        expectedUser.setId(userId);
        expectedUser.setName("John");
        expectedUser.setLastName("Doe");
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(expectedUser.getPassword())).thenReturn(encodedPassword);
        expectedUser.setEmail("john@example.com");
        expectedUser.setPassword("password");

        when(userPersistencePort.getUserById(userId)).thenReturn(expectedUser);

        // Act
        User result = userUseCase.getUserById(userId);

        // Assert
        assertNotNull(result, "El usuario no debería ser nulo");
        assertEquals(userId, result.getId(), "El ID del usuario debería coincidir");
        assertEquals("john@example.com", result.getEmail(), "El email del usuario debería coincidir");
    }

    @Test
    void testUpdateUser() {
        // Arrange
        User user = new User();
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);

        // Act
        userUseCase.updateUser(user);

        // Assert
        assertEquals(encodedPassword, user.getPassword());
        verify(userPersistencePort, times(1)).updateUser(user);
    }

    @Test
    void testDeleteUser() {
        // Arrange
        Long userId = 1L;

        // Act
        userUseCase.deleteUser(userId);

        // Assert
        verify(userPersistencePort, times(1)).deleteUser(userId);
    }

}