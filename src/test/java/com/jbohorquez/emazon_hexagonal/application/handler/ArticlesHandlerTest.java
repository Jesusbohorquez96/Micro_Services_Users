package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.ArticleRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.ArticleResponse;
import com.jbohorquez.emazon_hexagonal.application.mapper.ArticleRequestMapper;
import com.jbohorquez.emazon_hexagonal.application.mapper.ArticleResponseMapper;
import com.jbohorquez.emazon_hexagonal.domain.api.IArticleServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ArticlesHandlerTest {

    @Mock
    private ArticleRequestMapper articleRequestMapper;

    @Mock
    private ArticleResponseMapper articleResponseMapper;

    @Mock
    private IArticleServicePort articleServicePort;

    @InjectMocks
    private ArticlesHandler articlesHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getArticle() {
        Page<Article> articles = new PageImpl<>(Arrays.asList(new Article(), new Article()));
        when(articleServicePort.getArticles(anyInt(), anyInt(), anyBoolean())).thenReturn(articles);
        when(articleResponseMapper.toResponseList(any(Article.class))).thenReturn(new ArticleResponse());

        Page<ArticleResponse> result = articlesHandler.getArticle(0, 10, "true");

        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        verify(articleServicePort, times(1)).getArticles(anyInt(), anyInt(), anyBoolean());
    }

    @Test
    void saveArticleIn() {
        ArticleRequest articleRequest = new ArticleRequest();
        Article article = new Article();
        when(articleRequestMapper.toArticle(articleRequest)).thenReturn(article);

        articlesHandler.saveArticleIn(articleRequest);

        verify(articleServicePort, times(1)).saveArticle(article);
    }

    @Test
    void getArticle_withSortBy() {
        Page<Article> articles = new PageImpl<>(Arrays.asList(new Article(), new Article()));
        when(articleServicePort.getArticles(anyInt(), anyInt(), anyString(), anyBoolean())).thenReturn(articles);
        when(articleResponseMapper.toResponseList(any(Article.class))).thenReturn(new ArticleResponse());

        Page<ArticleResponse> result = articlesHandler.getArticle(0, 10, "name", "asc");

        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        verify(articleServicePort, times(1)).getArticles(anyInt(), anyInt(), anyString(), anyBoolean());
    }

    @Test
    void getArticleFrom() {
        List<Article> articles = Arrays.asList(new Article(), new Article());
        when(articleServicePort.getAllArticle()).thenReturn(articles);
        when(articleResponseMapper.toResponseList(any(Article.class))).thenReturn(new ArticleResponse());

        List<ArticleResponse> result = articlesHandler.getArticleFrom();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(articleServicePort, times(1)).getAllArticle();
    }

    @Test
    void getArticleFromById() {
        Long articleId = 1L;
        Article article = new Article();
        when(articleServicePort.getArticleById(articleId)).thenReturn(article);
        when(articleResponseMapper.toResponseList(article)).thenReturn(new ArticleResponse());

        ArticleResponse result = articlesHandler.getArticleFrom(articleId);

        assertNotNull(result);
        verify(articleServicePort, times(1)).getArticleById(articleId);
    }

    @Test
    void updateArticleIn() {
        ArticleRequest articleRequest = new ArticleRequest();
        Article article = new Article();
        when(articleRequestMapper.toArticle(articleRequest)).thenReturn(article);

        articlesHandler.updateArticleIn(articleRequest);

        verify(articleServicePort, times(1)).updateArticle(article);
    }

    @Test
    void deleteArticleFrom() {
        Long articleId = 1L;
        articlesHandler.deleteArticleFrom(articleId);

        verify(articleServicePort, times(1)).deleteArticle(articleId);
    }
}