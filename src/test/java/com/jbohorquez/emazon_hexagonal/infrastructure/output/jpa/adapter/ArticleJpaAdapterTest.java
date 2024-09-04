package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter;

import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.ArticleEntity;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.ArticleEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.IArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleJpaAdapterTest {

    @Mock
    private IArticleRepository articleRepository;

    @Mock
    private ArticleEntityMapper articleEntityMapper;

    @InjectMocks
    private ArticleJpaAdapter articleJpaAdapter;

    private Article article;
    private ArticleEntity articleEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        article = new Article(1L, "Article 1", "Description 1", 10, 99.99, null, new HashSet<>());
        articleEntity = new ArticleEntity();
        articleEntity.setId(1L);
        articleEntity.setName("Article 1");
        articleEntity.setDescription("Description 1");
    }

    @Test
    void testGetAllArticle_noDataFound() {
        when(articleRepository.findAll()).thenReturn(Collections.emptyList());

        try {
            articleJpaAdapter.getAllArticle();
            fail("Expected NoDataFoundException to be thrown");
        } catch (NoDataFoundException e) {
            // La excepción se lanzó correctamente
        }
    }

    @Test
    void testGetArticleById_notFound() {
        when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            articleJpaAdapter.getArticleById(1L);
            fail("Expected NoDataFoundException to be thrown");
        } catch (NoDataFoundException e) {
            // La excepción se lanzó correctamente
        }
    }

    @Test
    void testGetArticles_noDataFound() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        when(articleRepository.findAll(pageRequest)).thenReturn(Page.empty(pageRequest));

        try {
            articleJpaAdapter.getArticles(pageRequest);
            fail("Expected NoDataFoundException to be thrown");
        } catch (NoDataFoundException e) {
            // La excepción se lanzó correctamente
        }
    }

    @Test
    void testSaveArticle_success() {
        article.getCategories().add(new Category(1L, "Category 1", "Description 1")); // Asegúrate de que el artículo tenga al menos una categoría
        when(articleRepository.findByName(article.getName())).thenReturn(Optional.empty());
        when(articleEntityMapper.toEntity(article)).thenReturn(articleEntity);

        articleJpaAdapter.saveArticle(article);

        verify(articleRepository, times(1)).save(articleEntity);
    }

    @Test
    void testUpdateArticle_success() {
        when(articleEntityMapper.toEntity(article)).thenReturn(articleEntity);

        articleJpaAdapter.updateArticle(article);

        verify(articleRepository, times(1)).save(articleEntity);
    }

    @Test
    void testDeleteArticle_success() {
        doNothing().when(articleRepository).deleteById(1L);

        articleJpaAdapter.deleteArticle(1L);

        verify(articleRepository, times(1)).deleteById(1L);
    }


    @Test
    void testFindAll_success() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ArticleEntity> articleEntityPage = new PageImpl<>(Collections.singletonList(articleEntity));

        when(articleRepository.findAll(pageable)).thenReturn(articleEntityPage);
        when(articleEntityMapper.toArticle(articleEntity)).thenReturn(article);

        Page<Article> result = articleJpaAdapter.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(article, result.getContent().get(0));
    }
}