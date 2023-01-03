package com.chiva.phoneshop.mapper;

import com.chiva.phoneshop.dto.ProductDTO;
import com.chiva.phoneshop.model.Product;
import com.chiva.phoneshop.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", uses = {ModelService.class})
public interface ProductMapper {

    @Mapping(target = "model", source = "productDTO.modelId")
    public Product toProduct(ProductDTO productDTO);

    @Mapping(target = "modelId", source = "product.model.id")
    public ProductDTO toProductDTO(Product product);
}
