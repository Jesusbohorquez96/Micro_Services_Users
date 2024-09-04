package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter;

import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.DescriptionTooLongException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NameTooLongException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.BrandEntity;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.BrandEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.IBrandRepository;
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

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESCRIPTION_BRAND_MAX_LENGTH;
import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME_MAX_LENGTH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandJpaAdapterTest {

    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private BrandEntityMapper brandEntityMapper;

    @InjectMocks
    private BrandJpaAdapter brandJpaAdapter;

    private Brand brand;
    private BrandEntity brandEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brand = new Brand(1L, "BrandName", "BrandDescription");
        brandEntity = new BrandEntity();
        brandEntity.setId(1L);
        brandEntity.setName("BrandName");
        brandEntity.setDescription("BrandDescription");
    }

    @Test
    void saveBrand_success() {
        when(brandRepository.findByName(brand.getName())).thenReturn(Optional.empty());
        when(brandEntityMapper.toEntity(brand)).thenReturn(brandEntity);

        assertDoesNotThrow(() -> brandJpaAdapter.saveBrand(brand));

        verify(brandRepository, times(1)).save(brandEntity);
    }

    @Test
    void saveBrand_alreadyExists() {
        when(brandRepository.findByName(brand.getName())).thenReturn(Optional.of(brandEntity));

        assertThrows(AlreadyExistsException.class, () -> brandJpaAdapter.saveBrand(brand));
    }

    @Test
    void saveBrand_nameTooLong() {
        brand.setName("A".repeat(NAME_MAX_LENGTH + 1));

        assertThrows(NameTooLongException.class, () -> brandJpaAdapter.saveBrand(brand));
    }

    @Test
    void saveBrand_descriptionTooLong() {
        brand.setDescription("A".repeat(DESCRIPTION_BRAND_MAX_LENGTH + 1));

        assertThrows(DescriptionTooLongException.class, () -> brandJpaAdapter.saveBrand(brand));
    }

    @Test
    void getAllBrand_success() {
        when(brandRepository.findAll()).thenReturn(Collections.singletonList(brandEntity));
        when(brandEntityMapper.toBrandList(anyList())).thenReturn(Collections.singletonList(brand));

        List<Brand> brands = brandJpaAdapter.getAllBrand();

        assertNotNull(brands);
        assertEquals(1, brands.size());
        assertEquals(brand, brands.get(0));
    }

    @Test
    void getAllBrand_noDataFound() {
        when(brandRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, () -> brandJpaAdapter.getAllBrand());
    }

    @Test
    void getBrandById_success() {
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brandEntity));
        when(brandEntityMapper.toBrand(brandEntity)).thenReturn(brand);

        Brand foundBrand = brandJpaAdapter.getBrandById(1L);

        assertNotNull(foundBrand);
        assertEquals(brand, foundBrand);
    }

    @Test
    void getBrandById_notFound() {
        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AlreadyExistsException.class, () -> brandJpaAdapter.getBrandById(1L));
    }

    @Test
    void updateBrand_success() {
        when(brandRepository.findByName(brand.getName())).thenReturn(Optional.empty());
        when(brandEntityMapper.toEntity(brand)).thenReturn(brandEntity);

        assertDoesNotThrow(() -> brandJpaAdapter.updateBrand(brand));

        verify(brandRepository, times(1)).save(brandEntity);
    }

    @Test
    void deleteBrand_success() {
        doNothing().when(brandRepository).deleteById(1L);

        assertDoesNotThrow(() -> brandJpaAdapter.deleteBrand(1L));

        verify(brandRepository, times(1)).deleteById(1L);
    }

    @Test
    void getBrands_success() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<BrandEntity> brandEntityPage = new PageImpl<>(Collections.singletonList(brandEntity));

        when(brandRepository.findAll(pageRequest)).thenReturn(brandEntityPage);
        when(brandEntityMapper.toBrand(brandEntity)).thenReturn(brand);

        Page<Brand> result = brandJpaAdapter.getBrands(pageRequest);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(brand, result.getContent().get(0));
    }

    @Test
    void getBrands_noDataFound() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<BrandEntity> brandEntityPage = Page.empty(pageRequest);

        when(brandRepository.findAll(pageRequest)).thenReturn(brandEntityPage);

        assertThrows(NoDataFoundException.class, () -> brandJpaAdapter.getBrands(pageRequest));
    }

    @Test
    void findAll_success() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<BrandEntity> brandEntityPage = new PageImpl<>(Collections.singletonList(brandEntity));

        when(brandRepository.findAll(pageable)).thenReturn(brandEntityPage);
        when(brandEntityMapper.toBrand(brandEntity)).thenReturn(brand);

        Page<Brand> result = brandJpaAdapter.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(brand, result.getContent().get(0));
    }
}