package com.jbohorquez.microservices_users.infrastructure.output.jpa.adapter;

import com.jbohorquez.microservices_users.domain.model.Rol;
import com.jbohorquez.microservices_users.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.microservices_users.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.RolEntity;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper.RolEntityMapper;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.repository.IRolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RolJpaAdapterTest {

    @Mock
    private IRolRepository rolRepository;

    @Mock
    private RolEntityMapper rolEntityMapper;

    @InjectMocks
    private RolJpaAdapter rolJpaAdapter;

    private Rol rol;
    private RolEntity rolEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        rol = new Rol();
        rol.setName("Admin");
        rol.setDescription("Administrator role");

        rolEntity = new RolEntity();
        rolEntity.setName("Admin");
        rolEntity.setDescription("Administrator role");
    }

    @Test
    void testSaveRol_Success() {
        when(rolRepository.findRolByName(anyString())).thenReturn(Optional.empty());
        when(rolEntityMapper.toEntity(any(Rol.class))).thenReturn(rolEntity);

        rolJpaAdapter.saveRol(rol);

        verify(rolRepository, times(1)).save(any(RolEntity.class));
    }

    @Test
    void testGetAllRol_Success() {
        when(rolRepository.findAll()).thenReturn(Collections.singletonList(rolEntity));
        when(rolEntityMapper.toRolList(anyList())).thenReturn(Collections.singletonList(rol));

        assertDoesNotThrow(() -> {
            var result = rolJpaAdapter.getAllRol();
            assertFalse(result.isEmpty());
        });
    }

    @Test
    void testGetAllRol_NoDataFound() {
        when(rolRepository.findAll()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(NoDataFoundException.class, () -> rolJpaAdapter.getAllRol());

        assertEquals(NoDataFoundException.class, exception.getClass());
    }

    @Test
    void testGetRolById_Success() {
        when(rolRepository.findById(anyLong())).thenReturn(Optional.of(rolEntity));
        when(rolEntityMapper.toRol(any(RolEntity.class))).thenReturn(rol);

        Rol result = rolJpaAdapter.getRolById(1L);

        assertEquals("Admin", result.getName());
    }

    @Test
    void testGetRolById_AlreadyExists() {
        when(rolRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(AlreadyExistsException.class, () -> rolJpaAdapter.getRolById(1L));

        assertEquals(AlreadyExistsException.class, exception.getClass());
    }

    @Test
    void testUpdateRol_Success() {
        when(rolEntityMapper.toEntity(any(Rol.class))).thenReturn(rolEntity);

        rolJpaAdapter.updateRol(rol);

        verify(rolRepository, times(1)).save(any(RolEntity.class));
    }

    @Test
    void testDeleteRol_Success() {
        rolJpaAdapter.deleteRol(1L);

        verify(rolRepository, times(1)).deleteById(anyLong());
    }
}