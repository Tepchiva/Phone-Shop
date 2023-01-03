package com.chiva.phoneshop.mapper;

import com.chiva.phoneshop.dto.BrandDTO;
import com.chiva.phoneshop.model.Brand;

public class EntityMapper {
    public static Brand toBrand(BrandDTO brandDto) {
        Brand brand = new Brand();
        brand.setName(brandDto.getName());
        return brand;
    }

    public static BrandDTO toBrandDto(Brand brand) {
        BrandDTO brandDto = new BrandDTO();
        brandDto.setId(brand.getId());
        brandDto.setName(brand.getName());
        return  brandDto;
    }
}
