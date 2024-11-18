package com.jbohorquez.microservices_users.infrastructure.input.rest;

import com.jbohorquez.microservices_users.application.dto.RegisterRequest;
import com.jbohorquez.microservices_users.application.dto.UserResponse;
import com.jbohorquez.microservices_users.application.handler.IUsersHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class UsersRestControllerTest {

    @InjectMocks
    private UsersRestController usersRestController;

    @Mock
    private IUsersHandler usersHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getFromUser_ShouldReturnOkWithListOfUsers() {

        List<UserResponse> userList = new ArrayList<>();
        userList.add(new UserResponse());
        when(usersHandler.getFromUser()).thenReturn(userList);

        ResponseEntity<List<UserResponse>> response = usersRestController.getFromUser();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void registerAdmin_ShouldReturnOkWhenUserCreatedSuccessfully() {

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("admin@example.com");
        registerRequest.setPassword("password");
        registerRequest.setRol(ADMIN);
        doNothing().when(usersHandler).registerUser(registerRequest);

        ResponseEntity<String> response = (ResponseEntity<String>) usersRestController.registerAdmin(registerRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(USER_CREATED, response.getBody());
    }

    @Test
    @WithMockUser(roles = "ADMIN_AUX")
    void registerCustomer_ShouldReturnOkWhenCustomerCreatedSuccessfully() {

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("customer@example.com");
        registerRequest.setPassword("password");
        registerRequest.setRol(CUSTOMER);
        doNothing().when(usersHandler).registerUser(registerRequest);

        ResponseEntity<String> response = (ResponseEntity<String>) usersRestController.registerCustomer(registerRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(USER_CREATED, response.getBody());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void registerAuxBodega_ShouldReturnOkWhenAuxBodegaCreatedSuccessfully() {

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("auxbodega@example.com");
        registerRequest.setPassword("password");
        registerRequest.setRol(AUX_BODEGA);
        doNothing().when(usersHandler).registerUser(registerRequest);

        ResponseEntity<String> response = (ResponseEntity<String>) usersRestController.register(registerRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(USER_CREATED, response.getBody());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteFromUser_ShouldReturnOkWhenUserDeletedSuccessfully() {

        Long userId = 1L;
        doNothing().when(usersHandler).deleteFromUser(userId);

        ResponseEntity<Void> response = usersRestController.deleteFromUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void registerAdmin_ShouldReturnConflictWhenUserAlreadyExists() {

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("existing@example.com");
        registerRequest.setPassword("password");
        registerRequest.setRol(ADMIN);
        doNothing().when(usersHandler).registerUser(registerRequest);

        ResponseEntity<String> response = (ResponseEntity<String>) usersRestController.registerAdmin(registerRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(USER_CREATED, response.getBody());
    }
}