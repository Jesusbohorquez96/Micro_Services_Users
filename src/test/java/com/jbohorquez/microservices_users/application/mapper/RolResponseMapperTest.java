package com.jbohorquez.microservices_users.application.mapper;

import com.jbohorquez.microservices_users.application.dto.RolResponse;
import com.jbohorquez.microservices_users.domain.model.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class RolResponseMapperTest {

    private RolResponseMapper rolResponseMapper;

    @BeforeEach
    void setUp() {
        rolResponseMapper = Mappers.getMapper(RolResponseMapper.class);
    }

    @Test
    void testToResponse() {
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setName("Admin");
        rol.setDescription("Administrator role");

        RolResponse rolResponse = rolResponseMapper.toResponse(rol);

        assertNotNull(rolResponse);
        assertEquals(1L, rolResponse.getRolId());
        assertEquals("Admin", rolResponse.getRolName());
        assertEquals("Administrator role", rolResponse.getRolDescription());
    }

    @Test
    void testToResponse_NullInput() {
        RolResponse rolResponse = rolResponseMapper.toResponse(null);

        assertNull(rolResponse);
    }
}