package com.chiva.phoneshop.mapper;

import com.chiva.phoneshop.dto.BrandDto;
import com.chiva.phoneshop.model.Brand;

public class EntityMapper {
    public static Brand toBrand(BrandDto brandDto) {
        Brand brand = new Brand();
        brand.setName(brandDto.getName());
        return brand;
    }

    public static BrandDto toBrandDto(Brand brand) {
        BrandDto brandDto = new BrandDto();
        brandDto.setId(brand.getId());
        brandDto.setName(brand.getName());
        return  brandDto;
    }
}
