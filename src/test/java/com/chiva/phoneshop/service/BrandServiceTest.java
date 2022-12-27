package com.chiva.phoneshop.service;

import com.chiva.phoneshop.exception.ApiException;
import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.repository.BrandRepository;
import com.chiva.phoneshop.service.impl.BrandServiceImpl;
import com.chiva.phoneshop.spec.BrandSpecification;
import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;

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

    @BeforeEach
    void setup() {
        this.brand = new Brand(1, "Apple");
        this.brandService = new BrandServiceImpl(brandRepository);
        Mockito.when(brandRepository.findById(2)).thenReturn(Optional.empty());
        Mockito.when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
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
        Mockito.when(brandRepository.save(any(Brand.class))).thenAnswer(invocation -> {
            Brand brandEntity = invocation.getArgument(0);
            brandEntity.setId(1);
            return brandEntity;
        });

        // then
        Brand brandNew = brandService.save(brand);
        Assertions.assertEquals("Apple", brandNew.getName());
        Assertions.assertEquals(1, brandNew.getId());
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
        Mockito.verify(brandRepository, Mockito.times(1)).save(brand);
    }

    @Test
    void getByIdSuccess() {

        // given

        // when

        // moved to setup method
        // Mockito.when(brandRepository.findById(1)).thenReturn(Optional.of(brand));

        // then
        Brand newBrand = brandService.getById(1);
        Assertions.assertNotNull(newBrand);
        Assertions.assertEquals("Apple", newBrand.getName());
        Assertions.assertEquals(1, newBrand.getId());
    }

    @Test
    void getByIdError() {
        // give

        // when

        // moved to setup method
        // Mockito.when(brandRepository.findById(2)).thenReturn(Optional.empty());

        // then
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> brandService.getById(2))
                .isInstanceOf(ApiException.class)
                .hasMessageStartingWith("brand not found for id:");
    }

    @Test
    void update() {
        // given
        Brand brandUpdate = new Brand(1, "Apple v2");

        // when
        Brand brandAfterUpdate = brandService.update(1, brandUpdate);

        // then
        Mockito.verify(brandRepository, Mockito.times(1)).save(brandUpdate);

        // @TODO: check brandAfterUpdate.getName() if it equals to "Apple v2"
        //        Assertions.assertEquals(brandAfterUpdate.getName(), "Apple v2");
    }

    @Test
    void delete() {
        // given
        Integer brandToDelete = 1;

        // when

        // moved to setup method
        // Brand brand = new Brand(1, "Apple");
        // Mockito.when(brandRepository.findById(brandToDelete)).thenReturn(Optional.of(brand));
        brandService.delete(brandToDelete);

        // then
        Mockito.verify(brandRepository, Mockito.times(1)).delete(brand);
    }

    @Test
    void getBrands() {
        // given
        List<Brand> listBrands = List.of(
                new Brand(1, "Apple"),
                new Brand(2, "Samsung")
            );

        BrandSpecification brandSpecification = new BrandSpecification();
        // when
        Mockito.when(brandRepository.findAll(brandSpecification)).thenReturn(listBrands);

        List<Brand> brands = brandService.getBrands(brandSpecification);

        // then
        Assertions.assertEquals(2, brands.size());
        Assertions.assertEquals(listBrands.get(0).getName(), brands.get(0).getName());
        Assertions.assertEquals(listBrands.get(1).getName(), brands.get(1).getName());
    }
}