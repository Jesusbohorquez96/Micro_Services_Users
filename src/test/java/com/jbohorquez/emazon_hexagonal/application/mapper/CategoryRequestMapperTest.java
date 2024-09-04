package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryRequest;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRequestMapperTest {

    private CategoryRequestMapper categoryRequestMapper;

    @BeforeEach
    void setUp() {
        categoryRequestMapper = Mappers.getMapper(CategoryRequestMapper.class);
    }

    @Test
    void toCategory() {
        CategoryRequest categoryRequest = new CategoryRequest("CategoryName", "CategoryDescription");

        Category category = categoryRequestMapper.toCategory(categoryRequest);

        assertNotNull(category);
        assertEquals(categoryRequest.getName(), category.getName());
        assertEquals(categoryRequest.getDescription(), category.getDescription());
    }
}