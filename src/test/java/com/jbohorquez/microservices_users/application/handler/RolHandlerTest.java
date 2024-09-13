package com.jbohorquez.microservices_users.application.handler;

import com.jbohorquez.microservices_users.application.dto.RolRequest;
import com.jbohorquez.microservices_users.application.dto.RolResponse;
import com.jbohorquez.microservices_users.application.mapper.RolRequestMapper;
import com.jbohorquez.microservices_users.application.mapper.RolResponseMapper;
import com.jbohorquez.microservices_users.domain.api.IRolServicePort;
import com.jbohorquez.microservices_users.domain.model.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RolHandlerTest {

    @Mock
    private RolRequestMapper rolRequestMapper;

    @Mock
    private RolResponseMapper rolResponseMapper;

    @Mock
    private IRolServicePort rolServicePort;

    @InjectMocks
    private RolHandler rolHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveInRol() {
        RolRequest rolRequest = new RolRequest("Admin", "Administrator role");
        Rol rol = new Rol();

        when(rolRequestMapper.toRol(any(RolRequest.class))).thenReturn(rol);

        rolHandler.saveInRol(rolRequest);

        verify(rolRequestMapper, times(1)).toRol(rolRequest);
        verify(rolServicePort, times(1)).saveRol(rol);
    }

    @Test
    void testGetAllRol() {
        List<Rol> roles = Arrays.asList(
                new Rol(),
                new Rol()
        );
        List<RolResponse> expectedRolResponses = Arrays.asList(
                new RolResponse(1L, "Admin", "Administrator role"),
                new RolResponse(2L, "User", "User role")
        );

        when(rolServicePort.getAllRol()).thenReturn(roles);
        when(rolResponseMapper.toResponse(any(Rol.class))).thenReturn(expectedRolResponses.get(0), expectedRolResponses.get(1));

        List<RolResponse> actualRolResponses = rolHandler.getAllRol();

        assertEquals(expectedRolResponses.size(), actualRolResponses.size());
        verify(rolServicePort, times(1)).getAllRol();
        verify(rolResponseMapper, times(roles.size())).toResponse(any(Rol.class));
    }

    @Test
    void testGetRolById() {
        Rol rol = new Rol();
        RolResponse expectedResponse = new RolResponse(1L, "Admin", "Administrator role");

        when(rolServicePort.getRolById(1L)).thenReturn(rol);
        when(rolResponseMapper.toResponse(rol)).thenReturn(expectedResponse);

        RolResponse actualResponse = rolHandler.getRolById(1L);

        assertEquals(expectedResponse.getRolId(), actualResponse.getRolId());
        assertEquals(expectedResponse.getRolName(), actualResponse.getRolName());
        verify(rolServicePort, times(1)).getRolById(1L);
        verify(rolResponseMapper, times(1)).toResponse(rol);
    }

    @Test
    void testUpdateRol() {
        RolRequest rolRequest = new RolRequest("Admin", "Administrator role");
        Rol rol = new Rol();

        when(rolRequestMapper.toRol(any(RolRequest.class))).thenReturn(rol);

        rolHandler.updateRol(rolRequest);

        verify(rolRequestMapper, times(1)).toRol(rolRequest);
        verify(rolServicePort, times(1)).updateRol(rol);
    }

    @Test
    void testDeleteRol() {
        Long rolId = 1L;

        rolHandler.deleteRol(rolId);

        verify(rolServicePort, times(1)).deleteRol(rolId);
    }
}