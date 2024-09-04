package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import com.jbohorquez.emazon_hexagonal.application.handler.ICategoriesHandler;
import com.jbohorquez.emazon_hexagonal.infrastructure.exceptionhandler.ExceptionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CategoriesRestControllerTest {

    @Mock
    private ICategoriesHandler categoriesHandler;

    @InjectMocks
    private CategoriesRestController categoriesRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCategories() {
        List<CategoryResponse> categoryList = Arrays.asList(
                new CategoryResponse(1L, "Category1", "Description1"),
                new CategoryResponse(2L, "Category2", "Description2")
        );
        Page<CategoryResponse> categoryPage = new PageImpl<>(categoryList, PageRequest.of(0, 10, Sort.by("name").ascending()), categoryList.size());

        when(categoriesHandler.getCategories(0, 10, "asc")).thenReturn(categoryPage);

        ResponseEntity<Page<CategoryResponse>> response = categoriesRestController.getCategories(0, 10, "asc");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(categoryPage, response.getBody());
    }

    @Test
    void saveInCategory() {
        CategoryRequest categoryRequest = new CategoryRequest("New Category", "New Description");

        doNothing().when(categoriesHandler).saveInCategory(categoryRequest);

        ResponseEntity<Map<String, String>> response = categoriesRestController.saveInCategory(categoryRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Successful creation", response.getBody().get("message"));
    }

    @Test
    void getFromCategory() {
        List<CategoryResponse> categoryList = Arrays.asList(
                new CategoryResponse(1L, "Category1", "Description1"),
                new CategoryResponse(2L, "Category2", "Description2")
        );

        when(categoriesHandler.getFromCategory()).thenReturn(categoryList);

        ResponseEntity<List<CategoryResponse>> response = categoriesRestController.getFromCategory();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryList, response.getBody());
    }

    @Test
    void getFromCategoryById() {
        Long categoryId = 1L;
        CategoryResponse categoryResponse = new CategoryResponse(categoryId, "Category1", "Description1");

        when(categoriesHandler.getFromCategory(categoryId)).thenReturn(categoryResponse);

        ResponseEntity<CategoryResponse> response = categoriesRestController.getFromCategory(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryResponse, response.getBody());
    }

    @Test
    void updateInCategory() {
        CategoryRequest categoryRequest = new CategoryRequest("Updated Category", "Updated Description");

        doNothing().when(categoriesHandler).updateInCategory(categoryRequest);

        ResponseEntity<Void> response = categoriesRestController.updateInCategory(categoryRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteFromCategory() {
        Long categoryId = 1L;

        doNothing().when(categoriesHandler).deleteFromCategory(categoryId);

        ResponseEntity<Void> response = categoriesRestController.deleteFromCategory(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}