package com.chiva.phoneshop.mapper;

import com.chiva.phoneshop.dto.ModelDto;
import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.model.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapper {
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);
    @Mapping(target = "brand", source = "dto.brandId") /*we use when different field name*/
    Model toModel(ModelDto dto);

    @Mapping(target = "brandId", source = "model.brand.id")
    ModelDto toModelDto(Model model);

    @Mapping(target = "id", source = "brandId")
    @Mapping(target = "name", ignore = true)
    Brand toBrand(Integer brandId);
}
