package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.UserRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.UserResponse;
import com.jbohorquez.emazon_hexagonal.application.handler.IUsersHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    void testGetUsers() {
        // Arrange
        UserResponse userResponse = new UserResponse();
        Page<UserResponse> usersPage = new PageImpl<>(Arrays.asList(userResponse));
        when(usersHandler.getUsers(anyInt(), anyInt(), anyString())).thenReturn(usersPage);

        // Act
        ResponseEntity<Page<UserResponse>> response = usersRestController.getUsers(0, 10, "asc");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getContent().size());
        verify(usersHandler, times(1)).getUsers(anyInt(), anyInt(), anyString());
    }

    @Test
    void testGetFromUser() {
        // Arrange
        UserResponse userResponse = new UserResponse();
        List<UserResponse> users = Arrays.asList(userResponse);
        when(usersHandler.getFromUser()).thenReturn(users);

        // Act
        ResponseEntity<List<UserResponse>> response = usersRestController.getFromUser();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(usersHandler, times(1)).getFromUser();
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
    void testUpdateInUser() throws MethodArgumentNotValidException {
        // Arrange
        UserRequest userRequest = new UserRequest();

        // Act
        ResponseEntity<Void> response = usersRestController.updateInUser(userRequest);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usersHandler, times(1)).updateInUser(any(UserRequest.class));
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