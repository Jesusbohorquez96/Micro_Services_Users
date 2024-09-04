package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.BrandRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.BrandResponse;
import com.jbohorquez.emazon_hexagonal.application.mapper.BrandRequestMapper;
import com.jbohorquez.emazon_hexagonal.application.mapper.BrandResponseMapper;
import com.jbohorquez.emazon_hexagonal.domain.api.BrandServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IBrandsHandlerTest {

    @Mock
    private BrandRequestMapper brandRequestMapper;

    @Mock
    private BrandResponseMapper brandResponseMapper;

    @Mock
    private BrandServicePort brandServicePort;

    @InjectMocks
    private IBrandsHandler brandsHandler;

    private Brand brand;
    private BrandRequest brandRequest;
    private BrandResponse brandResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brand = new Brand(1L, "BrandName", "BrandDescription");
        brandRequest = new BrandRequest("BrandName", "BrandDescription");
        brandResponse = new BrandResponse(1L, "BrandName", "BrandDescription");
    }

    @Test
    void getBrands() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Brand> brandPage = new PageImpl<>(Collections.singletonList(brand), pageRequest, 1);

        when(brandServicePort.getBrands(0, 10, "asc")).thenReturn(brandPage);
        when(brandResponseMapper.toBrResponseList(brand)).thenReturn(brandResponse);

        Page<BrandResponse> result = brandsHandler.getBrands(0, 10, "asc");

        assertEquals(1, result.getTotalElements());
        assertEquals(brandResponse, result.getContent().get(0));
    }

    @Test
    void saveInBrand() {
        when(brandRequestMapper.toBrand(brandRequest)).thenReturn(brand);
        doNothing().when(brandServicePort).saveBrand(brand);

        brandsHandler.saveInBrand(brandRequest);

        verify(brandRequestMapper, times(1)).toBrand(brandRequest);
        verify(brandServicePort, times(1)).saveBrand(brand);
    }

    @Test
    void getFromBrand() {
        when(brandServicePort.getAllBrand()).thenReturn(Collections.singletonList(brand));
        when(brandResponseMapper.toBrResponseList(brand)).thenReturn(brandResponse);

        List<BrandResponse> result = brandsHandler.getFromBrand();

        assertEquals(1, result.size());
        assertEquals(brandResponse, result.get(0));
    }

    @Test
    void getFromBrandById() {
        when(brandServicePort.getBrandById(1L)).thenReturn(brand);
        when(brandResponseMapper.toBrResponseList(brand)).thenReturn(brandResponse);

        BrandResponse result = brandsHandler.getFromBrand(1L);

        assertEquals(brandResponse, result);
    }

    @Test
    void updateInBrand() {
        when(brandRequestMapper.toBrand(brandRequest)).thenReturn(brand);
        doNothing().when(brandServicePort).updateBrand(brand);

        brandsHandler.updateInBrand(brandRequest);

        verify(brandRequestMapper, times(1)).toBrand(brandRequest);
        verify(brandServicePort, times(1)).updateBrand(brand);
    }

    @Test
    void deleteFromBrand() {
        doNothing().when(brandServicePort).deleteBrand(1L);

        brandsHandler.deleteFromBrand(1L);

        verify(brandServicePort, times(1)).deleteBrand(1L);
    }
}