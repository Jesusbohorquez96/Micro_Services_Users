package com.jbohorquez.emazon_hexagonal.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RolTest {

    private Rol rol;

    @BeforeEach
    public void setUp() {
        rol = new Rol();
    }

    @Test
    public void testRolConstructorWithParameters() {
        Rol rolWithParams = new Rol("Admin", "Role with all permissions", 1L);

        assertNotNull(rolWithParams);
        assertEquals(1L, rolWithParams.getId());
        assertEquals("Admin", rolWithParams.getName());
        assertEquals("Role with all permissions", rolWithParams.getDescription());
    }

    @Test
    public void testRolDefaultConstructor() {
        assertNotNull(rol);
    }

    @Test
    public void testSetAndGetId() {
        rol.setId(2L);
        assertEquals(2L, rol.getId());
    }

    @Test
    public void testSetAndGetName() {
        rol.setName("User");
        assertEquals("User", rol.getName());
    }

    @Test
    public void testSetAndGetDescription() {
        rol.setDescription("Standard role");
        assertEquals("Standard role", rol.getDescription());
    }

    @Test
    public void testToString() {
        rol.setId(3L);
        rol.setName("Manager");
        rol.setDescription("Role for managers");

        String expectedString = "Rol{id=3, name='Manager', description='Role for managers'}";
        assertEquals(expectedString, rol.toString());
    }

    @Test
    void getId() {
    }
}
