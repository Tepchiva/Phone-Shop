package com.chiva.phoneshop.mapper;

import com.chiva.phoneshop.dto.BrandDto;
import com.chiva.phoneshop.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandDto toBrandDto(Brand brand);
    Brand toBrand(BrandDto brandDto);
}
