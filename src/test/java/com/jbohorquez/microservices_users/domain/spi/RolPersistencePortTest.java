package com.jbohorquez.microservices_users.domain.spi;

import com.jbohorquez.microservices_users.domain.model.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RolPersistencePortTest {

    @Mock
    private RolPersistencePort rolPersistencePort;

    private Rol rol;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rol = new Rol();
        rol.setId(1L);
        rol.setName("Admin");
        rol.setDescription("Administrator role");
    }

    @Test
    void testSaveRol() {
        doNothing().when(rolPersistencePort).saveRol(any(Rol.class));
        rolPersistencePort.saveRol(rol);
        verify(rolPersistencePort, times(1)).saveRol(rol);
    }

    @Test
    void testGetAllRol() {
        List<Rol> roles = Arrays.asList(rol);
        when(rolPersistencePort.getAllRol()).thenReturn(roles);

        List<Rol> result = rolPersistencePort.getAllRol();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Admin", result.get(0).getName());
    }

    @Test
    void testDeleteRol() {
        doNothing().when(rolPersistencePort).deleteRol(1L);
        rolPersistencePort.deleteRol(1L);
        verify(rolPersistencePort, times(1)).deleteRol(1L);
    }
}