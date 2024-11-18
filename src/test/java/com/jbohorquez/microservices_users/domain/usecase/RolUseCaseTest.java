package com.jbohorquez.microservices_users.domain.usecase;

import com.jbohorquez.microservices_users.domain.model.Rol;
import com.jbohorquez.microservices_users.domain.spi.RolPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RolUseCaseTest {

    @InjectMocks
    private RolUseCase rolUseCase;

    @Mock
    private RolPersistencePort rolPersistencePort;

    private Rol rol;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rol = new Rol();
    }

    @Test
    void saveRol_ShouldThrowExceptionWhenNameIsNull() {

        rol.setName(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> rolUseCase.saveRol(rol));
        assertEquals(NAME_REQUIRED, exception.getMessage());
        verify(rolPersistencePort, never()).saveRol(any());
    }

    @Test
    void getAllRol_ShouldReturnListOfRoles() {

        List<Rol> roles = new ArrayList<>();
        roles.add(rol);
        when(rolPersistencePort.getAllRol()).thenReturn(roles);

        List<Rol> result = rolUseCase.getAllRol();

        assertEquals(1, result.size());
        assertEquals(rol, result.get(0));
        verify(rolPersistencePort, times(1)).getAllRol();
    }

    @Test
    void deleteRol_ShouldDeleteRolSuccessfully() {

        Long rolId = 1L;
        doNothing().when(rolPersistencePort).deleteRol(rolId);

        rolUseCase.deleteRol(rolId);

        verify(rolPersistencePort, times(1)).deleteRol(rolId);
    }
}