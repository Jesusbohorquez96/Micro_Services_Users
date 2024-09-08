package com.jbohorquez.emazon_hexagonal.domain.spi;

import com.jbohorquez.emazon_hexagonal.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserPersistencePortTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("John");
        user.setEmail("john@example.com");
        user.setPassword("password123");
    }

    @Test
    void testSaveUser() {
        doNothing().when(userPersistencePort).saveUser(any(User.class));
        userPersistencePort.saveUser(user);
        verify(userPersistencePort, times(1)).saveUser(user);
    }

    @Test
    void testGetAllUser() {
        List<User> users = Arrays.asList(user);
        when(userPersistencePort.getAllUser()).thenReturn(users);

        List<User> result = userPersistencePort.getAllUser();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
    }

    @Test
    void testGetUserById() {
        when(userPersistencePort.getUserById(1L)).thenReturn(user);

        User result = userPersistencePort.getUserById(1L);
        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void testUpdateUser() {
        doNothing().when(userPersistencePort).updateUser(any(User.class));
        user.setName("Updated John");
        userPersistencePort.updateUser(user);
        verify(userPersistencePort, times(1)).updateUser(user);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userPersistencePort).deleteUser(1L);
        userPersistencePort.deleteUser(1L);
        verify(userPersistencePort, times(1)).deleteUser(1L);
    }

    @Test
    void testFindAllUser() {
        List<User> users = Arrays.asList(user);
        Page<User> page = new PageImpl<>(users);
        Pageable pageable = PageRequest.of(0, 10);
        when(userPersistencePort.findAllUser(pageable)).thenReturn(page);

        Page<User> result = userPersistencePort.findAllUser(pageable);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("John", result.getContent().get(0).getName());
    }

    @Test
    void testGetUsers() {
        List<User> users = Arrays.asList(user);
        Page<User> page = new PageImpl<>(users);
        PageRequest pageRequest = PageRequest.of(0, 10);
        when(userPersistencePort.getUsers(pageRequest)).thenReturn(page);

        Page<User> result = userPersistencePort.getUsers(pageRequest);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("John", result.getContent().get(0).getName());
    }

    @Test
    void testFindByEmail() {
        when(userPersistencePort.findByEmail("john@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userPersistencePort.findByEmail("john@example.com");
        assertTrue(result.isPresent());
        assertEquals("John", result.get().getName());
        assertEquals("john@example.com", result.get().getEmail());
    }
}