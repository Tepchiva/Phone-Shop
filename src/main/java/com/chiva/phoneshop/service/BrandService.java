package com.chiva.phoneshop.service;

import com.chiva.phoneshop.dto.BrandDto;
import com.chiva.phoneshop.model.Brand;

import java.util.List;

public interface BrandService {
    public Brand save(Brand entity);

    public Brand getById(Integer id);

    public Brand update(Integer id, BrandDto brandDto);

    public List<Brand> getAllBrands();

    public void delete(Integer id);
}
