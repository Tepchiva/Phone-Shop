package com.chiva.phoneshop.repository;

import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.utils.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// enable load applicant spring context
@DataJpaTest
class BrandRepositoryTest {

    @Autowired
    BrandRepository brandRepository;

    /*
    @BeforeEach
    void setUp() {
        brandRepository.deleteAll();
    }
     */

    @Test
    void existsByName() {
        // given
        Brand brand = new Brand();
        brand.setName("Apple");
        brandRepository.save(brand);

        // when
        boolean existBrand = brandRepository.existsByNameIgnoreCase("ApPle");
        boolean notExistBrand = brandRepository.existsByNameIgnoreCase("SAmsung");
        // then
        assertTrue(existBrand);
        assertFalse(notExistBrand);
    }

    @Test
    void findByIdIn() {
        List<Brand> brands = List.of(new Brand("Apple", Constant.STATUS_ACT), new Brand("Samsung", Constant.STATUS_ACT));

        brandRepository.saveAll(brands);
        List<Brand> brandList = brandRepository.findByIdIn(List.of(1, 2));
        assertEquals(2, brandList.size());
        assertEquals(1, brandList.get(0).getId());
        assertEquals("Apple", brandList.get(0).getName());

        assertEquals(2, brandList.get(1).getId());
        assertEquals("Samsung", brandList.get(1).getName());

        List<Brand> allBrands = brandRepository.findAll();
        assertEquals(2,allBrands.size());
    }
}