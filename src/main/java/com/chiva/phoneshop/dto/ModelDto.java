package com.chiva.phoneshop.dto;

import com.chiva.phoneshop.model.Brand;
import lombok.Data;
import org.springframework.core.annotation.AliasFor;

import javax.persistence.Column;

@Data
public class ModelDto {

    private Integer id;

    private String name;

    private BrandDto brandDto;
}
