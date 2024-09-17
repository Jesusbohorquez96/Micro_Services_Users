package com.jbohorquez.microservices_users.infrastructure.input.rest;

import com.jbohorquez.microservices_users.application.dto.UserResponse;
import com.jbohorquez.microservices_users.application.handler.IUsersHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class UsersRestControllerTest {

    @InjectMocks
    private UsersRestController usersRestController;

    @Mock
    private IUsersHandler usersHandler;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testGetFromUser() {
        List<UserResponse> mockUsers = new ArrayList<>();
        UserResponse user1 = new UserResponse();
        mockUsers.add(user1);

        when(usersHandler.getFromUser()).thenReturn(mockUsers);

        ResponseEntity<List<UserResponse>> response = usersRestController.getFromUser();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(null, response.getBody().get(0).getUserName());

        verify(usersHandler, times(1)).getFromUser();
    }

    @Test
    void testDeleteFromUser() {
        // Act
        ResponseEntity<Void> response = usersRestController.deleteFromUser(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(usersHandler, times(1)).deleteFromUser(1L);
    }
}