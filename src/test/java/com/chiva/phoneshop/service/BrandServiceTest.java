package com.chiva.phoneshop.service;

import com.chiva.phoneshop.exception.ApiException;
import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.repository.BrandRepository;
import com.chiva.phoneshop.service.impl.BrandServiceImpl;
import com.chiva.phoneshop.spec.BrandSpecification;
import com.chiva.phoneshop.utils.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)

// @MockitoSettings
// to skip unnecessary stubbing exception
// stubbing exception mean every in method setup '(BeforeEach)' doesn't use in every test method
@MockitoSettings(strictness = Strictness.LENIENT)
class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    private BrandService brandService;

    private Brand brand;

    @Captor
    private ArgumentCaptor<Brand> brandArgumentCaptor;

    @BeforeEach
    void setup() {
        this.brand = new Brand(1, "Apple", Constant.STATUS_ACT);
        this.brandService = new BrandServiceImpl(brandRepository);
        when(brandRepository.findByIdAndStatus(2, Constant.STATUS_ACT)).thenReturn(Optional.empty());
        when(brandRepository.findByIdAndStatus(1, Constant.STATUS_ACT)).thenReturn(Optional.of(brand));
    }

    // test save old style
    /*
    @Test
    public void saveOld() {
        // given
        Brand brand = new Brand();
        brand.setName("Apple");

        // when
        //        when(brandRepository.save(any(Brand.class))).thenAnswer(new Answer<Brand>() {
        //            @Override
        //            public Brand answer(InvocationOnMock invocation) throws Throwable {
        //                Brand brandEntity = invocation.getArgument(0);
        //                brandEntity.setId(1);
        //                return brandEntity;
        //            }
        //        });
        // use lambda instead
        when(brandRepository.save(any(Brand.class))).thenAnswer(invocation -> {
            Brand brandEntity = invocation.getArgument(0);
            brandEntity.setId(1);
            return brandEntity;
        });

        // then
        Brand brandNew = brandService.save(brand);

        assertEquals("Apple", brandNew.getName());

        assertEquals(1, brandNew.getId());
    }
    */

    @Test
    public void save() {
        // given
        Brand brand = new Brand();
        brand.setName("Apple");

        // when

        // then
        brandService.save(brand);
        verify(brandRepository, times(1)).save(brand);
    }

    @Test
    void getByIdSuccess() {

        // given

        // when

        // moved to setup method
        // when(brandRepository.findByIdAndStatus(1)).thenReturn(Optional.of(brand));

        // then
        Brand newBrand = brandService.getById(1);

        assertNotNull(newBrand);

        assertEquals("Apple", newBrand.getName());

        assertEquals(1, newBrand.getId());
    }

    @Test
    void getByIdError() {
        // give

        // when

        // moved to setup method
        // when(brandRepository.findByIdAndStatus(2)).thenReturn(Optional.empty());

        // then
        org.assertj.core.api.Assertions
                .assertThatThrownBy(() -> brandService.getById(2))
                .isInstanceOf(ApiException.class)
                .hasMessageStartingWith("Brand not found for id:");
    }

    @Test
    void update() {
        // given
        Brand brandUpdate = new Brand(5, "Apple v2", Constant.STATUS_ACT);

        // when
        brandService.update(1, brandUpdate);

        // then
        verify(brandRepository, atMostOnce()).findByIdAndStatus(1, Constant.STATUS_ACT);
         verify(brandRepository).save(brandArgumentCaptor.capture());
         assertEquals("Apple v2", brandArgumentCaptor.getValue().getName());
         assertEquals(1, brandArgumentCaptor.getValue().getId());
    }

    @Test
    void delete() {
        // given
        Integer brandToDelete = 1;

        // when

        // moved to setup method
        // Brand brand = new Brand(1, "Apple");
        // when(brandRepository.findByIdAndStatus(brandToDelete)).thenReturn(Optional.of(brand));
        brandService.delete(brandToDelete);

        // then
        verify(brandRepository).save(brandArgumentCaptor.capture());
        assertEquals(Constant.STATUS_DEL, brandArgumentCaptor.getValue().getStatus());
        assertEquals(Constant.STATUS_DEL, brand.getStatus());
    }

    @Test
    void getBrands() {
        // given
        List<Brand> listBrands = List.of(
                new Brand(1, "Apple", Constant.STATUS_ACT),
                new Brand(2, "Samsung", Constant.STATUS_ACT)
            );

        Map<String, String> params = new HashMap<>();

        // when
        when(brandRepository.findAll(any(BrandSpecification.class))).thenReturn(listBrands);
        List<Brand> brands = brandService.getBrands(params);

        // then
        assertEquals(2, brands.size());

        assertEquals(listBrands.get(0).getName(), brands.get(0).getName());

        assertEquals(listBrands.get(1).getName(), brands.get(1).getName());
    }
}