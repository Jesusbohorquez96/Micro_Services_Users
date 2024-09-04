package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper;

import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.CategoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryEntityMapperTest {

    private CategoryEntityMapper categoryEntityMapper;

    @BeforeEach
    void setUp() {
        categoryEntityMapper = Mappers.getMapper(CategoryEntityMapper.class);
    }

    @Test
    void toEntity() {
        Category category = new Category(1L, "CategoryName", "CategoryDescription");

        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);

        assertNotNull(categoryEntity);
        assertEquals(category.getId(), categoryEntity.getId());
        assertEquals(category.getName(), categoryEntity.getName());
        assertEquals(category.getDescription(), categoryEntity.getDescription());
    }

    @Test
    void toCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("CategoryName");
        categoryEntity.setDescription("CategoryDescription");

        Category category = categoryEntityMapper.toCategory(categoryEntity);

        assertNotNull(category);
        assertEquals(categoryEntity.getId(), category.getId());
        assertEquals(categoryEntity.getName(), category.getName());
        assertEquals(categoryEntity.getDescription(), category.getDescription());
    }

    @Test
    void toCategoryList() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("CategoryName");
        categoryEntity.setDescription("CategoryDescription");
        List<CategoryEntity> categoryEntityList = Collections.singletonList(categoryEntity);

        List<Category> categoryList = categoryEntityMapper.toCategoryList(categoryEntityList);

        assertNotNull(categoryList);
        assertEquals(1, categoryList.size());
        Category category = categoryList.get(0);
        assertEquals(categoryEntity.getId(), category.getId());
        assertEquals(categoryEntity.getName(), category.getName());
        assertEquals(categoryEntity.getDescription(), category.getDescription());
    }
}