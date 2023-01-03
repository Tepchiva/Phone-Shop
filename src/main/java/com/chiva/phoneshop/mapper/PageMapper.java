package com.chiva.phoneshop.mapper;

import com.chiva.phoneshop.dto.PageDTO;
import com.chiva.phoneshop.dto.PaginationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface PageMapper {
    PageMapper INSTANCE = Mappers.getMapper(PageMapper.class);

    @Mapping(target = "pageable", expression = "java(toPaginationDto(page))")
    @Mapping(target = "list", expression = "java(page.getContent())")
    PageDTO toPageDto(Page<?> page);

    @Mapping(target = "page", expression = "java(page.getNumber() + 1)")
    PaginationDTO toPaginationDto(Page<?> page);
}
