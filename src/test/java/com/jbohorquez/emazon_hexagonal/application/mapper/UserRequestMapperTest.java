package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.UserRequest;
import com.jbohorquez.emazon_hexagonal.domain.model.Rol;
import com.jbohorquez.emazon_hexagonal.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestMapperTest {

    private UserRequestMapper userRequestMapper;

    @BeforeEach
    void setUp() {
        userRequestMapper = Mappers.getMapper(UserRequestMapper.class);
    }

    @Test
    void testToUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("John");
        userRequest.setLastName("Doe");
        userRequest.setIdentityDocument(123456789L);
        userRequest.setPhone("+1234567890");
        userRequest.setBirthdate(LocalDate.of(1990, 1, 1));
        userRequest.setEmail("john.doe@example.com");
        userRequest.setPassword("Password@123");
        userRequest.setRol(1L);  // ID del rol

        User user = userRequestMapper.toUser(userRequest);

        assertNotNull(user);
        assertEquals("John", user.getName());
        assertEquals("Doe", user.getLastName());
        assertEquals(123456789L, user.getIdentityDocument());
        assertEquals("+1234567890", user.getPhone());
        assertEquals(LocalDate.of(1990, 1, 1), user.getBirthdate());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("Password@123", user.getPassword());

        assertNotNull(user.getRol());
        assertEquals(1L, user.getRol().getId());
    }

    @Test
    void testToUser_NullInput() {
        User user = userRequestMapper.toUser(null);

        assertNull(user);
    }

    @Test
    void testToRol() {
        Long rolId = 2L;
        Rol rol = userRequestMapper.toRol(rolId);

        assertNotNull(rol);
        assertEquals(rolId, rol.getId());
    }

    @Test
    void testToRol_NullInput() {
        Rol rol = userRequestMapper.toRol(null);

        assertNull(rol.getId());
    }
}