package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.ArticleRequest;
import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArticleRequestMapperTest {

    private ArticleRequestMapper articleRequestMapper;

    @BeforeEach
    void setUp() {
        articleRequestMapper = Mappers.getMapper(ArticleRequestMapper.class);
    }

    @Test
    void testToArticle() {
        // Arrange
        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setName("Sample Article");
        articleRequest.setBrand(1L);
        Set<Long> categoryIds = new HashSet<>();
        categoryIds.add(1L);
        categoryIds.add(2L);
        articleRequest.setCategories(categoryIds);

        // Act
        Article article = articleRequestMapper.toArticle(articleRequest);

        // Assert
        assertNotNull(article);
        assertEquals("Sample Article", article.getName());
        assertNotNull(article.getBrand());
        assertEquals(1L, article.getBrand().getId());
        assertNotNull(article.getCategories());
        assertEquals(2, article.getCategories().size());
        assertTrue(article.getCategories().stream().anyMatch(c -> c.getId().equals(1L)));
        assertTrue(article.getCategories().stream().anyMatch(c -> c.getId().equals(2L)));
    }

    @Test
    void testCategories() {
        // Arrange
        Set<Long> categoryIds = new HashSet<>();
        categoryIds.add(1L);
        categoryIds.add(2L);

        // Act
        Set<Category> categories = articleRequestMapper.Categories(categoryIds);

        // Assert
        assertNotNull(categories);
        assertEquals(2, categories.size());
        assertTrue(categories.stream().anyMatch(c -> c.getId().equals(1L)));
        assertTrue(categories.stream().anyMatch(c -> c.getId().equals(2L)));
    }

    @Test
    void testMapBrand() {
        // Act
        Brand brand = articleRequestMapper.map(1L);

        // Assert
        assertNotNull(brand);
        assertEquals(1L, brand.getId());
    }

    @Test
    void testMapBrand_Null() {
        // Act
        Brand brand = articleRequestMapper.map(null);

        // Assert
        assertNull(brand);
    }
}