package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.UserRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.UserResponse;
import com.jbohorquez.emazon_hexagonal.application.handler.IUsersHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.List;

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