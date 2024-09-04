package com.jbohorquez.emazon_hexagonal.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {

    private Brand brand;

    @BeforeEach
    void setUp() {
        brand = new Brand(1L, "BrandName", "BrandDescription");
    }

    @Test
    void getId() {
        assertEquals(1L, brand.getId());
    }

    @Test
    void setId() {
        brand.setId(2L);
        assertEquals(2L, brand.getId());
    }

    @Test
    void getName() {
        assertEquals("BrandName", brand.getName());
    }

    @Test
    void setName() {
        brand.setName("NewBrandName");
        assertEquals("NewBrandName", brand.getName());
    }

    @Test
    void getDescription() {
        assertEquals("BrandDescription", brand.getDescription());
    }

    @Test
    void setDescription() {
        brand.setDescription("NewBrandDescription");
        assertEquals("NewBrandDescription", brand.getDescription());
    }

    @Test
    void testToString() {
        String expected = "Brand{id=1, name='BrandName', description='BrandDescription'}";
        assertEquals(expected, brand.toString());
    }
}