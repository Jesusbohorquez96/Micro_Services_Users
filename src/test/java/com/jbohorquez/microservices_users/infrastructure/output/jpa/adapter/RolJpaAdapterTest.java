package com.jbohorquez.microservices_users.infrastructure.output.jpa.adapter;

import com.jbohorquez.microservices_users.domain.model.Rol;
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
    void testDeleteRol_Success() {
        rolJpaAdapter.deleteRol(1L);

        verify(rolRepository, times(1)).deleteById(anyLong());
    }
}