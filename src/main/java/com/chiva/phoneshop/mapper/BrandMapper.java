package com.chiva.phoneshop.mapper;

import com.chiva.phoneshop.dto.BrandDto;
import com.chiva.phoneshop.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandDto toBrandDto(Brand brand);
    Brand toBrand(BrandDto brandDto);

    // for update existing entity

    //    @Mapping(target = "id", expression = "java(target.getId())")
    @Mapping(ignore = true, target = "id")
    void update(@MappingTarget Brand target, Brand source);
}
