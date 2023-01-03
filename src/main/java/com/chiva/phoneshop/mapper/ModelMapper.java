package com.chiva.phoneshop.mapper;

import com.chiva.phoneshop.dto.ModelDTO;
import com.chiva.phoneshop.model.Model;
import com.chiva.phoneshop.service.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


// *note: (uses = {BrandService.class}) for help convert brandId to brand object
// if we try use '@Mapper(componentModel = "spring", uses = {BrandService.class})' in mpper,
// 'ModelMapper.INSTANCE.toModel(modelDto)' will not support with autowire of spring
// because it's static, it's lost context of spring
@Mapper(componentModel = "spring", uses = {BrandService.class})
public interface ModelMapper {
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    @Mapping(target = "brandId", source = "model.brand.id")
    ModelDTO toModelDto(Model model);

    @Mapping(target = "brand", source = "dto.brandId")
    Model toModel(ModelDTO dto);

    // old: (replace by uses = {BrandService.class})
    //    @Mapping(target = "id", source = "brandId")
    //    @Mapping(target = "name", ignore = true)
    //    Brand toBrand(Integer brandId);
}
