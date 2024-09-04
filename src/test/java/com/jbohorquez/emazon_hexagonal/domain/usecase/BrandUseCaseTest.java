package com.jbohorquez.emazon_hexagonal.domain.usecase;

import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import com.jbohorquez.emazon_hexagonal.domain.spi.BrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    @Mock
    private BrandPersistencePort brandPersistencePort;

    private BrandUseCase brandUseCase;

    private Brand brand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brand = new Brand(1L, "BrandName", "BrandDescription");

        brandUseCase = new BrandUseCase(brandPersistencePort);
    }

    @Test
    void saveBrand() {
        doNothing().when(brandPersistencePort).saveBrand(brand);
        brandUseCase.saveBrand(brand);
        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }

    @Test
    void getAllBrand() {
        when(brandPersistencePort.getAllBrand()).thenReturn(Arrays.asList(brand));
        List<Brand> brands = brandUseCase.getAllBrand();
        assertEquals(1, brands.size());
        assertEquals(brand, brands.get(0));
    }

    @Test
    void getBrandById() {
        when(brandPersistencePort.getBrandById(1L)).thenReturn(brand);
        Brand foundBrand = brandUseCase.getBrandById(1L);
        assertEquals(brand, foundBrand);
    }

    @Test
    void updateBrand() {
        doNothing().when(brandPersistencePort).updateBrand(brand);
        brandUseCase.updateBrand(brand);
        verify(brandPersistencePort, times(1)).updateBrand(brand);
    }

    @Test
    void deleteBrand() {
        doNothing().when(brandPersistencePort).deleteBrand(1L);
        brandUseCase.deleteBrand(1L);
        verify(brandPersistencePort, times(1)).deleteBrand(1L);
    }

    @Test
    void getBrandsPagedAscending() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name").ascending());
        Page<Brand> brandPage = new PageImpl<>(Arrays.asList(brand), pageRequest, 1);

        when(brandPersistencePort.findAll(pageRequest)).thenReturn(brandPage);
        Page<Brand> result = brandUseCase.getBrands(0, 10, true);

        assertEquals(1, result.getTotalElements());
        assertEquals(brand, result.getContent().get(0));
    }

    @Test
    void getBrandsPagedDescending() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name").descending());
        Page<Brand> brandPage = new PageImpl<>(Arrays.asList(brand), pageRequest, 1);

        when(brandPersistencePort.findAll(pageRequest)).thenReturn(brandPage);
        Page<Brand> result = brandUseCase.getBrands(0, 10, false);

        assertEquals(1, result.getTotalElements());
        assertEquals(brand, result.getContent().get(0));
    }
}
