package com.chiva.phoneshop.service.impl;

import com.chiva.phoneshop.dto.BrandDto;
import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.repository.BrandRepository;
import com.chiva.phoneshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.net.http.HttpRequest;
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
    public Brand getById(Integer id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isPresent()) return optionalBrand.get();
        else
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "brand not found");
    }

    @Override
    public Brand update(Integer id, BrandDto brandDto) {

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
    public void delete(Integer id) {
        Brand brand = getById(id);
        brandRepository.delete(brand);
    }
}
