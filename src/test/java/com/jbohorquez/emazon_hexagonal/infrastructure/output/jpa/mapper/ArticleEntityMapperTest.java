package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper;

import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.ArticleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleEntityMapperTest {

    private ArticleEntityMapper articleEntityMapper;

    @BeforeEach
    void setUp() {
        articleEntityMapper = Mappers.getMapper(ArticleEntityMapper.class);
    }

    @Test
    void toEntity_ShouldMapArticleToArticleEntity() {
        // Arrange
        Article article = new Article();
        article.setId(1L);
        article.setName("Test Article");
        article.setDescription("Test Description");
        article.setStock(100);
        article.setPrice(99.99);

        // Act
        ArticleEntity articleEntity = articleEntityMapper.toEntity(article);

        // Assert
        assertNotNull(articleEntity);
        assertEquals(article.getId(), articleEntity.getId());
        assertEquals(article.getName(), articleEntity.getName());
        assertEquals(article.getDescription(), articleEntity.getDescription());
        assertEquals(article.getStock(), articleEntity.getStock());
        assertEquals(article.getPrice(), articleEntity.getPrice());
    }

    @Test
    void toArticle_ShouldMapArticleEntityToArticle() {
        // Arrange
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setId(1L);
        articleEntity.setName("Test Article");
        articleEntity.setDescription("Test Description");
        articleEntity.setStock(100);
        articleEntity.setPrice(99.99);

        // Act
        Article article = articleEntityMapper.toArticle(articleEntity);

        // Assert
        assertNotNull(article);
        assertEquals(articleEntity.getId(), article.getId());
        assertEquals(articleEntity.getName(), article.getName());
        assertEquals(articleEntity.getDescription(), article.getDescription());
        assertEquals(articleEntity.getStock(), article.getStock());
        assertEquals(articleEntity.getPrice(), article.getPrice());
    }

    @Test
    void toArticleList_ShouldMapArticleEntityListToArticleList() {
        // Arrange
        ArticleEntity articleEntity1 = new ArticleEntity();
        articleEntity1.setId(1L);
        articleEntity1.setName("Article 1");
        articleEntity1.setDescription("Description 1");
        articleEntity1.setStock(50);
        articleEntity1.setPrice(49.99);

        ArticleEntity articleEntity2 = new ArticleEntity();
        articleEntity2.setId(2L);
        articleEntity2.setName("Article 2");
        articleEntity2.setDescription("Description 2");
        articleEntity2.setStock(150);
        articleEntity2.setPrice(149.99);

        List<ArticleEntity> articleEntityList = Arrays.asList(articleEntity1, articleEntity2);

        // Act
        List<Article> articleList = articleEntityMapper.toArticleList(articleEntityList);

        // Assert
        assertNotNull(articleList);
        assertEquals(2, articleList.size());

        assertEquals(articleEntity1.getId(), articleList.get(0).getId());
        assertEquals(articleEntity1.getName(), articleList.get(0).getName());
        assertEquals(articleEntity1.getDescription(), articleList.get(0).getDescription());
        assertEquals(articleEntity1.getStock(), articleList.get(0).getStock());
        assertEquals(articleEntity1.getPrice(), articleList.get(0).getPrice());

        assertEquals(articleEntity2.getId(), articleList.get(1).getId());
        assertEquals(articleEntity2.getName(), articleList.get(1).getName());
        assertEquals(articleEntity2.getDescription(), articleList.get(1).getDescription());
        assertEquals(articleEntity2.getStock(), articleList.get(1).getStock());
        assertEquals(articleEntity2.getPrice(), articleList.get(1).getPrice());
    }
}
