package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter;

import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.DescriptionTooLongException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NameTooLongException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.CategoryEntity;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESCRIPTION_MAX_LENGTH;
import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME_MAX_LENGTH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryJpaAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    private Category category;
    private CategoryEntity categoryEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category(1L, "CategoryName", "CategoryDescription");
        categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("CategoryName");
        categoryEntity.setDescription("CategoryDescription");
    }

    @Test
    void saveCategory_success() {
        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.empty());
        when(categoryEntityMapper.toEntity(category)).thenReturn(categoryEntity);

        assertDoesNotThrow(() -> categoryJpaAdapter.saveCategory(category));

        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    void saveCategory_alreadyExists() {
        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(categoryEntity));

        assertThrows(AlreadyExistsException.class, () -> categoryJpaAdapter.saveCategory(category));
    }

    @Test
    void saveCategory_nameTooLong() {
        category.setName("A".repeat(NAME_MAX_LENGTH + 1));

        assertThrows(NameTooLongException.class, () -> categoryJpaAdapter.saveCategory(category));
    }

    @Test
    void saveCategory_descriptionTooLong() {
        category.setDescription("A".repeat(DESCRIPTION_MAX_LENGTH + 1));

        assertThrows(DescriptionTooLongException.class, () -> categoryJpaAdapter.saveCategory(category));
    }

    @Test
    void getAllCategory_success() {
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(categoryEntity));
        when(categoryEntityMapper.toCategoryList(anyList())).thenReturn(Collections.singletonList(category));

        List<Category> categories = categoryJpaAdapter.getAllCategory();

        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals(category, categories.get(0));
    }

    @Test
    void getAllCategory_noDataFound() {
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, () -> categoryJpaAdapter.getAllCategory());
    }

    @Test
    void getCategoryById_success() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toCategory(categoryEntity)).thenReturn(category);

        Category foundCategory = categoryJpaAdapter.getCategoryById(1L);

        assertNotNull(foundCategory);
        assertEquals(category, foundCategory);
    }

    @Test
    void getCategoryById_notFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AlreadyExistsException.class, () -> categoryJpaAdapter.getCategoryById(1L));
    }

    @Test
    void updateCategory_success() {
        when(categoryEntityMapper.toEntity(category)).thenReturn(categoryEntity);

        assertDoesNotThrow(() -> categoryJpaAdapter.updateCategory(category));

        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    void deleteCategory_success() {
        doNothing().when(categoryRepository).deleteById(1L);

        assertDoesNotThrow(() -> categoryJpaAdapter.deleteCategory(1L));

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    void getCategories_success() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<CategoryEntity> categoryEntityPage = new PageImpl<>(Collections.singletonList(categoryEntity));

        when(categoryRepository.findAll(pageRequest)).thenReturn(categoryEntityPage);
        when(categoryEntityMapper.toCategory(categoryEntity)).thenReturn(category);

        Page<Category> result = categoryJpaAdapter.getCategories(pageRequest);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(category, result.getContent().get(0));
    }

    @Test
    void getCategories_noDataFound() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<CategoryEntity> categoryEntityPage = Page.empty(pageRequest);

        when(categoryRepository.findAll(pageRequest)).thenReturn(categoryEntityPage);

        assertThrows(NoDataFoundException.class, () -> categoryJpaAdapter.getCategories(pageRequest));
    }

    @Test
    void findAll_success() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<CategoryEntity> categoryEntityPage = new PageImpl<>(Collections.singletonList(categoryEntity));

        when(categoryRepository.findAll(pageable)).thenReturn(categoryEntityPage);
        when(categoryEntityMapper.toCategory(categoryEntity)).thenReturn(category);

        Page<Category> result = categoryJpaAdapter.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(category, result.getContent().get(0));
    }
}