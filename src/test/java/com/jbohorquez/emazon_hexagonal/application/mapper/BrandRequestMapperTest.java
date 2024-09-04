package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.BrandRequest;
import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class BrandRequestMapperTest {

    private BrandRequestMapper brandRequestMapper;

    @BeforeEach
    void setUp() {
        brandRequestMapper = Mappers.getMapper(BrandRequestMapper.class);
    }

    @Test
    void toBrand() {
        BrandRequest brandRequest = new BrandRequest("BrandName", "BrandDescription");

        Brand brand = brandRequestMapper.toBrand(brandRequest);

        assertNotNull(brand);
        assertEquals(brandRequest.getName(), brand.getName());
        assertEquals(brandRequest.getDescription(), brand.getDescription());
    }
}