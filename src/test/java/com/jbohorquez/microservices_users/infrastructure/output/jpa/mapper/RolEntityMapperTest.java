package com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper;

import com.jbohorquez.microservices_users.domain.model.Rol;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.RolEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RolEntityMapperTest {

    private RolEntityMapper rolEntityMapper;

    @BeforeEach
    void setUp() {
        rolEntityMapper = Mappers.getMapper(RolEntityMapper.class);
    }

    @Test
    void testToEntity() {
        // Crea un Rol para mapearlo a RolEntity
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setName("Admin");
        rol.setDescription("Administrator role");

        // Mapea el Rol a RolEntity
        RolEntity rolEntity = rolEntityMapper.toEntity(rol);

        // Verifica que el mapeo se haya realizado correctamente
        assertNotNull(rolEntity);
        assertEquals(rol.getId(), rolEntity.getId());
        assertEquals(rol.getName(), rolEntity.getName());
        assertEquals(rol.getDescription(), rolEntity.getDescription());
    }

    @Test
    void testToRol() {
        // Crea un RolEntity para mapearlo a Rol
        RolEntity rolEntity = new RolEntity();
        rolEntity.setId(1L);
        rolEntity.setName("Admin");
        rolEntity.setDescription("Administrator role");

        // Mapea el RolEntity a Rol
        Rol rol = rolEntityMapper.toRol(rolEntity);

        // Verifica que el mapeo se haya realizado correctamente
        assertNotNull(rol);
        assertEquals(rolEntity.getId(), rol.getId());
        assertEquals(rolEntity.getName(), rol.getName());
        assertEquals(rolEntity.getDescription(), rol.getDescription());
    }

    @Test
    void testToRolList() {
        // Crea una lista de RolEntity para mapearla a una lista de Rol
        RolEntity rolEntity1 = new RolEntity();
        rolEntity1.setId(1L);
        rolEntity1.setName("Admin");
        rolEntity1.setDescription("Administrator role");

        RolEntity rolEntity2 = new RolEntity();
        rolEntity2.setId(2L);
        rolEntity2.setName("User");
        rolEntity2.setDescription("User role");

        List<RolEntity> rolEntityList = Arrays.asList(rolEntity1, rolEntity2);

        // Mapea la lista de RolEntity a una lista de Rol
        List<Rol> rolList = rolEntityMapper.toRolList(rolEntityList);

        // Verifica que el mapeo se haya realizado correctamente
        assertNotNull(rolList);
        assertEquals(2, rolList.size());
        assertEquals(rolEntity1.getId(), rolList.get(0).getId());
        assertEquals(rolEntity2.getId(), rolList.get(1).getId());
    }
}