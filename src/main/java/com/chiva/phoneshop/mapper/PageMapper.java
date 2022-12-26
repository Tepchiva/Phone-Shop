package com.chiva.phoneshop.mapper;

import com.chiva.phoneshop.dto.PageDto;
import com.chiva.phoneshop.dto.PaginationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface PageMapper {
    PageMapper INSTANCE = Mappers.getMapper(PageMapper.class);

    @Mapping(target = "pageable", expression = "java(toPaginationDto(page))")
    @Mapping(target = "list", expression = "java(page.getContent())")
    PageDto toPageDto(Page<?> page);

    @Mapping(target = "page", expression = "java(page.getNumber() + 1)")
    PaginationDto toPaginationDto(Page<?> page);
}
