package com.jbohorquez.microservices_users.domain.api;

import com.jbohorquez.microservices_users.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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