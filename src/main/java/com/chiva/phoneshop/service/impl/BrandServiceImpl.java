package com.chiva.phoneshop.service.impl;

import com.chiva.phoneshop.exception.ApiException;
import com.chiva.phoneshop.exception.ResourceNotFoundException;
import com.chiva.phoneshop.mapper.BrandMapper;
import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.repository.BrandRepository;
import com.chiva.phoneshop.service.BrandService;
import com.chiva.phoneshop.spec.BrandSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand save(Brand entity) {
        log.info("Creating new brand {}", entity);
        if (brandRepository.existsByNameIgnoreCase(entity.getName().trim()))
            throw new ApiException(HttpStatus.BAD_REQUEST,"ERR-002", "Brand already exist.");
        return brandRepository.save(entity);
    }

    @Override
    public Brand getById(Integer id) {

         return brandRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", id));
    }

    // old
    //    @Override
    //    public Brand update(Integer id, BrandDto brandDto) {
    //
    //        Brand brand = this.getById(id);
    //        brand.setName(brandDto.getName());
    //        brandRepository.save(brand);
    //        return brand;
    //    }

    @Override
    public Brand update(Integer id, Brand brand) {
        Brand targetBrand = this.getById(id);
        BrandMapper.INSTANCE.update(targetBrand, brand);
        return brandRepository.save(targetBrand);
    }

    @Override
    public List<Brand> getBrands(Map<String, String> params) {

        BrandSpecification brandSpecification = new BrandSpecification(
                MapUtils.getInteger(params, "brandId", null),
                params.getOrDefault("brandName", null)
        );

        return brandRepository.findAll(brandSpecification);
    }

    @Override
    public void delete(Integer id) {
        Brand brand = getById(id);
        brandRepository.delete(brand);
    }
}
