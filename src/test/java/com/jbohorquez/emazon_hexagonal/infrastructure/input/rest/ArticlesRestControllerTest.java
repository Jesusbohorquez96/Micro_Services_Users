package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.ArticleRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.ArticleResponse;
import com.jbohorquez.emazon_hexagonal.application.handler.ArticlesHandler;
import com.jbohorquez.emazon_hexagonal.enums.SortByFieldsArticles;
import com.jbohorquez.emazon_hexagonal.infrastructure.exceptionhandler.ExceptionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticlesRestControllerTest {

    @Mock
    private ArticlesHandler articlesHandler;

    @InjectMocks
    private ArticlesRestController articlesRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetArticles() {
        Page<ArticleResponse> page = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);
        when(articlesHandler.getArticle(0, 10, "name", "asc")).thenReturn(page);
        SortByFieldsArticles sortBy = SortByFieldsArticles.NAME;
        ResponseEntity<Page<ArticleResponse>> response = articlesRestController.getArticles(0, 10, sortBy, "asc");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());
        verify(articlesHandler, times(1)).getArticle(0, 10, "name", "asc");
    }

    @Test
    void testSaveArticleIn_Success() {
        ArticleRequest articleRequest = new ArticleRequest();
        doNothing().when(articlesHandler).saveArticleIn(articleRequest);

        ResponseEntity<Map<String, String>> response = articlesRestController.saveArticleIn(articleRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ExceptionResponse.SUCCESSFUL_CREATION.getMessage(), response.getBody().get("message"));
        verify(articlesHandler, times(1)).saveArticleIn(articleRequest);
    }

    @Test
    void testSaveArticleIn_Conflict() {
        ArticleRequest articleRequest = new ArticleRequest();
        doThrow(new RuntimeException()).when(articlesHandler).saveArticleIn(articleRequest);

        ResponseEntity<Map<String, String>> response = articlesRestController.saveArticleIn(articleRequest);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(ExceptionResponse.ALREADY_EXISTS.getMessage(), response.getBody().get("message"));
        verify(articlesHandler, times(1)).saveArticleIn(articleRequest);
    }

    @Test
    void testGetArticleFromById() {
        ArticleResponse articleResponse = new ArticleResponse();
        when(articlesHandler.getArticleFrom(1L)).thenReturn(articleResponse);

        ResponseEntity<ArticleResponse> response = articlesRestController.getArticleFrom(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(articleResponse, response.getBody());
        verify(articlesHandler, times(1)).getArticleFrom(1L);
    }
}