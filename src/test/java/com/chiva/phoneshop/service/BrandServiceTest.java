package com.chiva.phoneshop.service;

import com.chiva.phoneshop.exception.ApiException;
import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.repository.BrandRepository;
import com.chiva.phoneshop.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    private BrandService brandService;

    @BeforeEach
    void setup() {
        this.brandService = new BrandServiceImpl(brandRepository);
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
        Brand brand = new Brand(1, "Apple");

        // when
        Mockito.when(brandRepository.findById(1)).thenReturn(Optional.of(brand));

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
        Mockito.when(brandRepository.findById(2)).thenReturn(Optional.empty());

        // then
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> brandService.getById(2))
                .isInstanceOf(ApiException.class)
                .hasMessageStartingWith("brand not found for id:");
    }
}