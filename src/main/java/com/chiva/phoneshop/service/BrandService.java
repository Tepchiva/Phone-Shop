package com.chiva.phoneshop.service;

import com.chiva.phoneshop.dto.BrandDto;
import com.chiva.phoneshop.exception.ApiException;
import com.chiva.phoneshop.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    public Brand save(Brand entity);

    public Brand getById(Integer id) throws ApiException;

    public Brand update(Integer id, BrandDto brandDto) throws ApiException;

    public List<Brand> getAllBrands();

    public void delete(Integer id) throws ApiException;
}
