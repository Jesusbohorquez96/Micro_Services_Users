package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.BrandResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class BrandResponseMapperTest {

    private BrandResponseMapper brandResponseMapper;

    @BeforeEach
    void setUp() {
        brandResponseMapper = Mappers.getMapper(BrandResponseMapper.class);
    }

    @Test
    void toBrResponseList() {
        Brand brand = new Brand(1L, "BrandName", "BrandDescription");

        BrandResponse brandResponse = brandResponseMapper.toBrResponseList(brand);

        assertNotNull(brandResponse);
        assertEquals(brand.getId(), brandResponse.getBrandId());
        assertEquals(brand.getName(), brandResponse.getBrandName());
        assertEquals(brand.getDescription(), brandResponse.getBrandDescription());
    }
}