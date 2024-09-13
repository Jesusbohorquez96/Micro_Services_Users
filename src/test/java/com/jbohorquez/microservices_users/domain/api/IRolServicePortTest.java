package com.jbohorquez.microservices_users.domain.api;

import com.jbohorquez.microservices_users.domain.model.Rol;
import com.jbohorquez.microservices_users.domain.spi.RolPersistencePort;
import com.jbohorquez.microservices_users.domain.usecase.RolUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IRolServicePortTest {

    @Mock
    private RolPersistencePort rolPersistencePort;

    @InjectMocks
    private RolUseCase rolUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveRol() {
        Rol rol = new Rol("Admin", "Role with all permissions", 1L);
        doNothing().when(rolPersistencePort).saveRol(rol);

        rolUseCase.saveRol(rol);

        verify(rolPersistencePort, times(1)).saveRol(rol);
    }

    @Test
    public void testGetAllRol() {
        Rol rol1 = new Rol("Admin", "Admin role", 1L);
        Rol rol2 = new Rol("User", "User role", 2L);
        when(rolPersistencePort.getAllRol()).thenReturn(List.of(rol1, rol2));

        List<Rol> roles = rolUseCase.getAllRol();

        assertNotNull(roles);
        assertEquals(2, roles.size());
        verify(rolPersistencePort, times(1)).getAllRol();
    }

    @Test
    public void testGetRolById() {
        Rol rol = new Rol("Admin", "Admin role", 1L);
        when(rolPersistencePort.getRolById(1L)).thenReturn(rol);

        Rol result = rolUseCase.getRolById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Admin", result.getName());
        verify(rolPersistencePort, times(1)).getRolById(1L);
    }

    @Test
    public void testUpdateRol() {
        Rol rol = new Rol("Admin", "Updated role", 1L);
        doNothing().when(rolPersistencePort).updateRol(rol);

        rolUseCase.updateRol(rol);

        verify(rolPersistencePort, times(1)).updateRol(rol);
    }

    @Test
    public void testDeleteRol() {
        doNothing().when(rolPersistencePort).deleteRol(1L);

        rolUseCase.deleteRol(1L);

        verify(rolPersistencePort, times(1)).deleteRol(1L);
    }
}