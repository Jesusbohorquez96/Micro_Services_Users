package com.jbohorquez.emazon_hexagonal.application.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RolResponseTest {

    private RolResponse rolResponse;

    @BeforeEach
    void setUp() {
        rolResponse = new RolResponse(1L, "Admin", "Admin Role");
    }

    @Test
    void testConstructor() {
        RolResponse rol = new RolResponse(1L, "Admin", "Admin Role");
        assertNull(rol.getRolId()); // Esto depende de cómo manejes los valores en el constructor
    }

    @Test
    void testGettersAndSetters() {
        rolResponse.setRolId(1L);
        rolResponse.setRolName("Admin");
        rolResponse.setRolDescription("Admin Role");

        assertEquals(1L, rolResponse.getRolId());
        assertEquals("Admin", rolResponse.getRolName());
        assertEquals("Admin Role", rolResponse.getRolDescription());
    }

    @Test
    void testGetName() {
        assertNull(rolResponse.getName());
    }

    @Test
    void testSettersAndGettersIndependently() {
        rolResponse.setRolId(2L);
        assertEquals(2L, rolResponse.getRolId());

        rolResponse.setRolName("User");
        assertEquals("User", rolResponse.getRolName());

        rolResponse.setRolDescription("User Role");
        assertEquals("User Role", rolResponse.getRolDescription());
    }
}