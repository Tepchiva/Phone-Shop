package com.chiva.phoneshop.service;

import com.chiva.phoneshop.dto.BrandDto;
import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.spec.BrandSpecification;

import java.util.List;

public interface BrandService {
    public Brand save(Brand entity);

    public Brand getById(Integer id);

    // old
    //    public Brand update(Integer id, BrandDto brandDto);
    public Brand update(Integer id, Brand brand);

    public List<Brand> getBrands(BrandSpecification brandSpecification);

    public void delete(Integer id);
}
