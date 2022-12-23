package com.chiva.phoneshop.mapper;

import com.chiva.phoneshop.dto.ModelDto;
import com.chiva.phoneshop.model.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapper {
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);
    @Mapping(target = "brand", source = "dto.brandDto") /*we use when different field name*/
    Model toModel(ModelDto dto);

    @Mapping(target = "brandDto", source = "model.brand")
    ModelDto toModelDto(Model model);
}
