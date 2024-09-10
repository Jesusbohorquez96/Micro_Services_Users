package com.jbohorquez.emazon_hexagonal.domain.api;

import com.jbohorquez.emazon_hexagonal.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class IUserServicePortTest {

    @Mock
    private IUserServicePort userServicePort;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
    }

    @Test
    void testSaveInUser() {
        doNothing().when(userServicePort).saveInUser(any(User.class));
        userServicePort.saveInUser(user);
        verify(userServicePort, times(1)).saveInUser(user);
    }

    @Test
    void testGetUserById() {
        when(userServicePort.getUserById(1L)).thenReturn(user);

        User result = userServicePort.getUserById(1L);
        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void testUpdateUser() {
        doNothing().when(userServicePort).updateUser(any(User.class));
        user.setLastName("Updated");
        userServicePort.updateUser(user);
        verify(userServicePort, times(1)).updateUser(user);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userServicePort).deleteUser(1L);
        userServicePort.deleteUser(1L);
        verify(userServicePort, times(1)).deleteUser(1L);
    }

    @Test
    void testFindByEmail() {
        when(userServicePort.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userServicePort.findByEmail("john.doe@example.com");
        assertTrue(result.isPresent());
        assertEquals("John", result.get().getName());
    }
}