package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper;

import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.BrandEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrandEntityMapperTest {

    private BrandEntityMapper brandEntityMapper;

    @BeforeEach
    void setUp() {
        brandEntityMapper = Mappers.getMapper(BrandEntityMapper.class);
    }

    @Test
    void toEntity() {
        Brand brand = new Brand(1L, "BrandName", "BrandDescription");

        BrandEntity brandEntity = brandEntityMapper.toEntity(brand);

        assertNotNull(brandEntity);
        assertEquals(brand.getId(), brandEntity.getId());
        assertEquals(brand.getName(), brandEntity.getName());
        assertEquals(brand.getDescription(), brandEntity.getDescription());
    }

    @Test
    void toBrand() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1L);
        brandEntity.setName("BrandName");
        brandEntity.setDescription("BrandDescription");

        Brand brand = brandEntityMapper.toBrand(brandEntity);

        assertNotNull(brand);
        assertEquals(brandEntity.getId(), brand.getId());
        assertEquals(brandEntity.getName(), brand.getName());
        assertEquals(brandEntity.getDescription(), brand.getDescription());
    }

    @Test
    void toBrandList() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1L);
        brandEntity.setName("BrandName");
        brandEntity.setDescription("BrandDescription");
        List<BrandEntity> brandEntityList = Collections.singletonList(brandEntity);

        List<Brand> brandList = brandEntityMapper.toBrandList(brandEntityList);

        assertNotNull(brandList);
        assertEquals(1, brandList.size());
        Brand brand = brandList.get(0);
        assertEquals(brandEntity.getId(), brand.getId());
        assertEquals(brandEntity.getName(), brand.getName());
        assertEquals(brandEntity.getDescription(), brand.getDescription());
    }
}