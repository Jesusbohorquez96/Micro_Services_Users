package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.UserResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Rol;
import com.jbohorquez.emazon_hexagonal.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseMapperTest {

    private UserResponseMapper userResponseMapper;

    @BeforeEach
    void setUp() {
        userResponseMapper = Mappers.getMapper(UserResponseMapper.class);
    }

    @Test
    void testToResponseList() {
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setLastName("Doe");
        user.setIdentityDocument(123456789L);
        user.setPhone("+1234567890");
        user.setBirthdate(LocalDate.of(1990, 1, 1));
        user.setEmail("john.doe@example.com");
        user.setPassword("Password@123");

        Rol rol = new Rol();
        rol.setName("Admin");
        user.setRol(rol);

        UserResponse userResponse = userResponseMapper.toResponseList(user);

        assertNotNull(userResponse);
        assertEquals(1L, userResponse.getUserId());
        assertEquals("John", userResponse.getUserName());
        assertEquals("Doe", userResponse.getUserLastName());
        assertEquals(123456789L, userResponse.getUserIdentityDocument());
        assertEquals("+1234567890", userResponse.getUserPhone());
        assertEquals(LocalDate.of(1990, 1, 1), userResponse.getUserBirthdate());
        assertEquals("john.doe@example.com", userResponse.getUserEmail());
        assertEquals("Password@123", userResponse.getUserPassword());

        assertEquals("Admin", userResponse.getUserRol());
    }

    @Test
    void testToResponseList_NullInput() {
        UserResponse userResponse = userResponseMapper.toResponseList(null);

        assertNull(userResponse);
    }

    @Test
    void testToRol() {
        Rol rol = new Rol();
        rol.setName("User");

        String rolName = userResponseMapper.toRol(rol);

        assertEquals("User", rolName);
    }

    @Test
    void testToRol_NullInput() {
        String rolName = userResponseMapper.toRol(null);

        assertNull(rolName);
    }
}