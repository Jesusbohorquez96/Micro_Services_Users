package com.jbohorquez.emazon_hexagonal.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category(1L, "CategoryName", "CategoryDescription");
    }

    @Test
    void getId() {
        assertEquals(1L, category.getId());
    }

    @Test
    void setId() {
        category.setId(2L);
        assertEquals(2L, category.getId());
    }

    @Test
    void getName() {
        assertEquals("CategoryName", category.getName());
    }

    @Test
    void setName() {
        category.setName("NewCategoryName");
        assertEquals("NewCategoryName", category.getName());
    }

    @Test
    void getDescription() {
        assertEquals("CategoryDescription", category.getDescription());
    }

    @Test
    void setDescription() {
        category.setDescription("NewCategoryDescription");
        assertEquals("NewCategoryDescription", category.getDescription());
    }

    @Test
    void testToString() {
        String expected = "Category{id=1, name='CategoryName', description='CategoryDescription'}";
        assertEquals(expected, category.toString());
    }
}
