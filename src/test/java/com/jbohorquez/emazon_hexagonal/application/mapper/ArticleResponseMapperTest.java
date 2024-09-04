package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.ArticleBrandResponse;
import com.jbohorquez.emazon_hexagonal.application.dto.ArticleCategoryResponse;
import com.jbohorquez.emazon_hexagonal.application.dto.ArticleResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArticleResponseMapperTest {

    private ArticleResponseMapper articleResponseMapper;

    @BeforeEach
    void setUp() {
        articleResponseMapper = Mappers.getMapper(ArticleResponseMapper.class);
    }

    @Test
    void toResponseList_ShouldMapArticleToArticleResponse() {
        // Arrange
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("BrandName");

        Category category = new Category();
        category.setId(1L);
        category.setName("CategoryName");

        Set<Category> categories = new HashSet<>();
        categories.add(category);

        Article article = new Article();
        article.setId(1L);
        article.setName("ArticleName");
        article.setDescription("ArticleDescription");
        article.setStock(10);
        article.setPrice(99.99);
        article.setBrand(brand);
        article.setCategories(categories);

        // Act
        ArticleResponse articleResponse = articleResponseMapper.toResponseList(article);

        // Assert
        assertNotNull(articleResponse);
        assertEquals(article.getId(), articleResponse.getArticleId());
        assertEquals(article.getName(), articleResponse.getArticleName());
        assertEquals(article.getDescription(), articleResponse.getArticleDescription());
        assertEquals(article.getStock(), articleResponse.getArticleStock());
        assertEquals(article.getPrice(), articleResponse.getArticlePrice());

        assertNotNull(articleResponse.getArticleBrand());
        assertEquals(brand.getId(), articleResponse.getArticleBrand().getBrandId());
        assertEquals(brand.getName(), articleResponse.getArticleBrand().getBrandName());

        assertNotNull(articleResponse.getArticleCategories());
        assertEquals(1, articleResponse.getArticleCategories().size());

        ArticleCategoryResponse categoryResponse = articleResponse.getArticleCategories().iterator().next();
        assertEquals(category.getId(), categoryResponse.getCategoryId());
        assertEquals(category.getName(), categoryResponse.getCategoryName());
    }

    @Test
    void mapBrandToArticleBrandResponse() {

        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("BrandName");


        ArticleBrandResponse brandResponse = articleResponseMapper.map(brand);

        assertNotNull(brandResponse, "El resultado del mapeo no debería ser null");
        assertNotNull(brandResponse.getBrandId(), "El ID de la marca mapeada no debería ser null");
        assertNotNull(brandResponse.getBrandName(), "El nombre de la marca mapeada no debería ser null");
    }
}