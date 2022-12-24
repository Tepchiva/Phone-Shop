package com.chiva.phoneshop.service.impl;

import com.chiva.phoneshop.dto.BrandDto;
import com.chiva.phoneshop.exception.ApiException;
import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.repository.BrandRepository;
import com.chiva.phoneshop.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand save(Brand entity) {
        return brandRepository.save(entity);
    }

    @Override
    public Brand getById(Integer id) throws ApiException {

         return brandRepository
                .findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "brand not found for id: %d.".formatted(id)));
    }

    @Override
    public Brand update(Integer id, BrandDto brandDto) throws ApiException {
        
        Brand brand = this.getById(id);
        brand.setName(brandDto.getName());
        brandRepository.save(brand);
        return brand;
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public void delete(Integer id) throws ApiException {
        Brand brand = getById(id);
        brandRepository.delete(brand);
    }
}
