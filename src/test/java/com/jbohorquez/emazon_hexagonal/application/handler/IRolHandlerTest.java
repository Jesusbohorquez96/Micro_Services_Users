package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.RolRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.RolResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IRolHandlerTest {

    @Mock
    private IRolHandler rolHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveInRol() {
        RolRequest rolRequest = new RolRequest("Admin", "Administrator role");

        rolHandler.saveInRol(rolRequest);

        verify(rolHandler, times(1)).saveInRol(rolRequest);
    }

    @Test
    void testGetAllRol() {
        List<RolResponse> expectedRolList = Arrays.asList(
                new RolResponse(1L, "Admin", "Administrator role"),
                new RolResponse(2L, "User", "User role")
        );

        when(rolHandler.getAllRol()).thenReturn(expectedRolList);

        List<RolResponse> actualRolList = rolHandler.getAllRol();

        assertEquals(expectedRolList.size(), actualRolList.size());
        verify(rolHandler, times(1)).getAllRol();
    }

    @Test
    void testGetRolById() {
        RolResponse expectedRol = new RolResponse(1L, "Admin", "Administrator role");

        when(rolHandler.getRolById(1L)).thenReturn(expectedRol);

        RolResponse actualRol = rolHandler.getRolById(1L);

        assertEquals(expectedRol.getRolId(), actualRol.getRolId());
        assertEquals(expectedRol.getRolName(), actualRol.getRolName());
        assertEquals(expectedRol.getRolDescription(), actualRol.getRolDescription());
        verify(rolHandler, times(1)).getRolById(1L);
    }

    @Test
    void testUpdateRol() {
        RolRequest rolRequest = new RolRequest("Admin", "Administrator role");

        rolHandler.updateRol(rolRequest);

        verify(rolHandler, times(1)).updateRol(rolRequest);
    }

    @Test
    void testDeleteRol() {
        Long rolId = 1L;

        rolHandler.deleteRol(rolId);

        verify(rolHandler, times(1)).deleteRol(rolId);
    }
}