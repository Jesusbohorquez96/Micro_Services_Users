package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.BrandRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.BrandResponse;
import com.jbohorquez.emazon_hexagonal.application.handler.BrandsHandler;
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
import static org.mockito.Mockito.*;

class BrandsRestControllerTest {


    @Mock
    private BrandsHandler brandsHandler;

    @InjectMocks
    private BrandsRestController brandsRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBrands() {
        List<BrandResponse> brandList = Arrays.asList(
                new BrandResponse(1L, "Brand1", "Description1"),
                new BrandResponse(2L, "Brand2", "Description2")
        );
        Page<BrandResponse> brandPage = new PageImpl<>(brandList, PageRequest.of(0, 10, Sort.by("name").ascending()), brandList.size());

        when(brandsHandler.getBrands(0, 10, "asc")).thenReturn(brandPage);

        ResponseEntity<Page<BrandResponse>> response = brandsRestController.getBrands(0, 10, "asc");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(brandPage, response.getBody());
    }

    @Test
    void saveInBrand() {
        BrandRequest brandRequest = new BrandRequest("New Brand", "New Description");

        doNothing().when(brandsHandler).saveInBrand(brandRequest);

        ResponseEntity<Map<String, String>> response = brandsRestController.saveInBrand(brandRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ExceptionResponse.SUCCESSFUL_CREATION.getMessage(), response.getBody().get("message"));
    }



    @Test
    void getFromBrand() {
        List<BrandResponse> brandList = Arrays.asList(
                new BrandResponse(1L, "Brand1", "Description1"),
                new BrandResponse(2L, "Brand2", "Description2")
        );

        when(brandsHandler.getFromBrand()).thenReturn(brandList);

        ResponseEntity<List<BrandResponse>> response = brandsRestController.getFromBrand();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brandList, response.getBody());
    }

    @Test
    void getFromBrandById() {
        Long brandId = 1L;
        BrandResponse brandResponse = new BrandResponse(brandId, "Brand1", "Description1");

        when(brandsHandler.getFromBrand(brandId)).thenReturn(brandResponse);

        ResponseEntity<BrandResponse> response = brandsRestController.getFromBrand(brandId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brandResponse, response.getBody());
    }

    @Test
    void updateInBrand() {
        BrandRequest brandRequest = new BrandRequest("Updated Brand", "Updated Description");

        doNothing().when(brandsHandler).updateInBrand(brandRequest);

        ResponseEntity<Void> response = brandsRestController.updateInBrand(brandRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteFromBrand() {
        Long brandId = 1L;

        doNothing().when(brandsHandler).deleteFromBrand(brandId);

        ResponseEntity<Void> response = brandsRestController.deleteFromBrand(brandId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(brandsHandler, times(1)).deleteFromBrand(brandId);
    }
}