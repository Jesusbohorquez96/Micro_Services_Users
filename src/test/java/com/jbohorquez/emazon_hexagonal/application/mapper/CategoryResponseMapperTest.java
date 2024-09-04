package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class CategoryResponseMapperTest {

    private CategoryResponseMapper categoryResponseMapper;

    @BeforeEach
    void setUp() {
        categoryResponseMapper = Mappers.getMapper(CategoryResponseMapper.class);
    }

    @Test
    void toResponseList() {
        Category category = new Category(1L, "CategoryName", "CategoryDescription");

        CategoryResponse categoryResponse = categoryResponseMapper.toResponseList(category);

        assertNotNull(categoryResponse);
        assertEquals(category.getId(), categoryResponse.getCategoryId());
        assertEquals(category.getName(), categoryResponse.getCategoryName());
        assertEquals(category.getDescription(), categoryResponse.getCategoryDescription());
    }
}