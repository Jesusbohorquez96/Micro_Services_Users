package com.jbohorquez.microservices_users.application.mapper;

import com.jbohorquez.microservices_users.application.dto.RolRequest;
import com.jbohorquez.microservices_users.domain.model.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

class RolRequestMapperTest {

    private RolRequestMapper rolRequestMapper;

    @BeforeEach
    void setUp() {
        rolRequestMapper = Mappers.getMapper(RolRequestMapper.class);
    }

    @Test
    void testToRol() {
        RolRequest rolRequest = new RolRequest();
        rolRequest.setName("Admin");
        rolRequest.setDescription("Administrator role");

        Rol rol = rolRequestMapper.toRol(rolRequest);

        assertNotNull(rol);
        assertEquals("Admin", rol.getName());
        assertEquals("Administrator role", rol.getDescription());
    }

    @Test
    void testToRol_NullInput() {
        Rol rol = rolRequestMapper.toRol(null);

        assertNull(rol);
    }
}
