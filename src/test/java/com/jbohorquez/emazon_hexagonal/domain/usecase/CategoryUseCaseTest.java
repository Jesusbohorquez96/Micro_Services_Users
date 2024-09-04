package com.jbohorquez.emazon_hexagonal.domain.usecase;

import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import com.jbohorquez.emazon_hexagonal.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category(1L, "CategoryName", "CategoryDescription");
    }

    @Test
    void saveCategory() {
        doNothing().when(categoryPersistencePort).saveCategory(category);
        categoryUseCase.saveCategory(category);
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void getAllCategory() {
        when(categoryPersistencePort.getAllCategory()).thenReturn(Arrays.asList(category));
        List<Category> categories = categoryUseCase.getAllCategory();
        assertEquals(1, categories.size());
        assertEquals(category, categories.get(0));
    }

    @Test
    void getCategoryById() {
        when(categoryPersistencePort.getCategoryById(1L)).thenReturn(category);
        Category foundCategory = categoryUseCase.getCategoryById(1L);
        assertEquals(category, foundCategory);
    }

    @Test
    void updateCategory() {
        doNothing().when(categoryPersistencePort).updateCategory(category);
        categoryUseCase.updateCategory(category);
        verify(categoryPersistencePort, times(1)).updateCategory(category);
    }

    @Test
    void deleteCategory() {
        doNothing().when(categoryPersistencePort).deleteCategory(1L);
        categoryUseCase.deleteCategory(1L);
        verify(categoryPersistencePort, times(1)).deleteCategory(1L);
    }

    @Test
    void getCategoriesPagedWithSortDirection() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "name"));
        Page<Category> categoryPage = new PageImpl<>(Collections.singletonList(category), pageRequest, 1);

        when(categoryPersistencePort.findAll(pageRequest)).thenReturn(categoryPage);
        Page<Category> result = categoryUseCase.getCategories(0, 10, Sort.Direction.DESC);

        assertEquals(1, result.getTotalElements());
        assertEquals(category, result.getContent().get(0));
    }


    @Test
    void getCategoriesPagedAscending() {
        Sort sort = Sort.by("name").ascending();
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name").ascending());
        Page<Category> categoryPage = new PageImpl<>(Arrays.asList(category), pageRequest, 1);

        when(categoryPersistencePort.findAll(pageRequest)).thenReturn(categoryPage);
        Page<Category> result = categoryUseCase.getCategories(0, 10, true);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(category, result.getContent().get(0));
    }

}