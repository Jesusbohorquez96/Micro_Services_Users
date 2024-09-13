package com.jbohorquez.microservices_users.infrastructure.input.rest;

import com.jbohorquez.microservices_users.application.dto.RolRequest;
import com.jbohorquez.microservices_users.application.dto.RolResponse;
import com.jbohorquez.microservices_users.application.handler.IRolHandler;
import com.jbohorquez.microservices_users.infrastructure.exception.AllExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RolesRestControllerTest {

    @Mock
    private IRolHandler rolHandler;

    @InjectMocks
    private RolesRestController rolesRestController;

    private RolRequest rolRequest;
    private RolResponse rolResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rolRequest = new RolRequest("Admin", "Administrator role");
        rolResponse = new RolResponse(1L, "Admin", "Administrator role");
    }

    @Test
    void testGetAllRol_Success() {
        when(rolHandler.getAllRol()).thenReturn(List.of(rolResponse));

        ResponseEntity<List<RolResponse>> response = rolesRestController.getAllRol();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        verify(rolHandler, times(1)).getAllRol();
    }

    @Test
    void testSaveInRol_Success() {
        doNothing().when(rolHandler).saveInRol(any(RolRequest.class));

        ResponseEntity<Map<String, String>> response = rolesRestController.saveInRol(rolRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Successful creation", response.getBody().get("message"));
        verify(rolHandler, times(1)).saveInRol(any(RolRequest.class));
    }

    @Test
    void testSaveInRol_AlreadyExists() {
        doThrow(new AllExistsException("Rol already exists")).when(rolHandler).saveInRol(any(RolRequest.class));

        ResponseEntity<Map<String, String>> response = rolesRestController.saveInRol(rolRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertEquals("Rol already exists", Objects.requireNonNull(response.getBody()).get("message"));

        verify(rolHandler, times(1)).saveInRol(any(RolRequest.class));
    }

    @Test
    void testGetRolById_Success() {
        when(rolHandler.getRolById(anyLong())).thenReturn(rolResponse);

        ResponseEntity<RolResponse> response = rolesRestController.getRolById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rolResponse, response.getBody());
        verify(rolHandler, times(1)).getRolById(anyLong());
    }

    @Test
    void testUpdateRol_Success() {
        doNothing().when(rolHandler).updateRol(any(RolRequest.class));

        ResponseEntity<Map<String, String>> response = rolesRestController.updateRol(rolRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(rolHandler, times(1)).updateRol(any(RolRequest.class));
    }

    @Test
    void testDeleteRol_Success() {
        doNothing().when(rolHandler).deleteRol(anyLong());

        ResponseEntity<Map<String, String>> response = rolesRestController.deleteRol(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(rolHandler, times(1)).deleteRol(anyLong());
    }
}