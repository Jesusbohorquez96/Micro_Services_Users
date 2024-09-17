package com.jbohorquez.microservices_users.domain.usecase;
import com.jbohorquez.microservices_users.domain.model.Rol;
import com.jbohorquez.microservices_users.domain.spi.RolPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RolUseCaseTest {

    @Mock
    private RolPersistencePort rolPersistencePort;

    @InjectMocks
    private RolUseCase rolUseCase;

    private Rol rol;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        rol = new Rol("Admin", "Role with full permissions", 1L);
    }

    @Test
    public void testSaveRol() {
        doNothing().when(rolPersistencePort).saveRol(rol);

        rolUseCase.saveRol(rol);

        verify(rolPersistencePort, times(1)).saveRol(rol);
    }

    @Test
    public void testGetAllRol() {
        when(rolPersistencePort.getAllRol()).thenReturn(List.of(rol));

        List<Rol> roles = rolUseCase.getAllRol();

        assertNotNull(roles);
        assertEquals(1, roles.size());
        assertEquals("Admin", roles.get(0).getName());
        verify(rolPersistencePort, times(1)).getAllRol();
    }

    @Test
    public void testDeleteRol() {
        doNothing().when(rolPersistencePort).deleteRol(1L);

        rolUseCase.deleteRol(1L);

        verify(rolPersistencePort, times(1)).deleteRol(1L);
    }
}