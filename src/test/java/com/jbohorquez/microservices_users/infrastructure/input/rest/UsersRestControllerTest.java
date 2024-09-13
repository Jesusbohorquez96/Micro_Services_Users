package com.jbohorquez.microservices_users.infrastructure.input.rest;

import com.jbohorquez.microservices_users.application.dto.UserResponse;
import com.jbohorquez.microservices_users.application.handler.IUsersHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
    void testGetFromUserById() {
        // Arrange
        UserResponse userResponse = new UserResponse();
        when(usersHandler.getFromUser(anyLong())).thenReturn(userResponse);

        // Act
        ResponseEntity<UserResponse> response = usersRestController.getFromUser(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponse, response.getBody());
        verify(usersHandler, times(1)).getFromUser(anyLong());
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