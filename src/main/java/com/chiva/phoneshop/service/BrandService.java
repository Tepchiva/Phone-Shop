package com.chiva.phoneshop.service;

import com.chiva.phoneshop.dto.BrandDto;
import com.chiva.phoneshop.exception.ApiException;
import com.chiva.phoneshop.model.Brand;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BrandService {
    public Brand save(Brand entity);

    public Brand getById(Integer id);

    // old
    //    public Brand update(Integer id, BrandDto brandDto);
    public Brand update(Integer id, Brand brand);

    public List<Brand> getBrands(Map<String, String> params);

    public void delete(Integer id);
}
