package com.jbohorquez.microservices_users.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;
    private Rol rol;

    @BeforeEach
    public void setUp() {
        rol = new Rol("Admin", "Role with all permissions", 1L);
        user = new User();
    }

    @Test
    public void testUserConstructorWithParameters() {
        User userWithParams = new User(1L, "John", "Doe", 123456789L, "+123456789", LocalDate.of(1990, 1, 1), "john.doe@example.com", "password123", rol);

        assertNotNull(userWithParams);
        assertEquals(1L, userWithParams.getId());
        assertEquals("John", userWithParams.getName());
        assertEquals("Doe", userWithParams.getLastName());
        assertEquals(123456789L, userWithParams.getIdentityDocument());
        assertEquals("+123456789", userWithParams.getPhone());
        assertEquals(LocalDate.of(1990, 1, 1), userWithParams.getBirthdate());
        assertEquals("john.doe@example.com", userWithParams.getEmail());
        assertEquals("password123", userWithParams.getPassword());
        assertEquals(rol, userWithParams.getRol());
    }

    @Test
    public void testUserDefaultConstructor() {
        assertNotNull(user);
    }

    @Test
    public void testSetAndGetId() {
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    public void testSetAndGetName() {
        user.setName("Jane");
        assertEquals("Jane", user.getName());
    }

    @Test
    public void testSetAndGetLastName() {
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());
    }

    @Test
    public void testSetAndGetIdentityDocument() {
        user.setIdentityDocument(987654321L);
        assertEquals(987654321L, user.getIdentityDocument());
    }

    @Test
    public void testSetAndGetPhone() {
        user.setPhone("+987654321");
        assertEquals("+987654321", user.getPhone());
    }

    @Test
    public void testSetAndGetBirthdate() {
        LocalDate birthdate = LocalDate.of(1992, 2, 2);
        user.setBirthdate(birthdate);
        assertEquals(birthdate, user.getBirthdate());
    }

    @Test
    public void testSetAndGetEmail() {
        user.setEmail("jane.smith@example.com");
        assertEquals("jane.smith@example.com", user.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        user.setPassword("securePassword123");
        assertEquals("securePassword123", user.getPassword());
    }

    @Test
    public void testSetAndGetRol() {
        user.setRol(rol);
        assertEquals(rol, user.getRol());
    }
}
