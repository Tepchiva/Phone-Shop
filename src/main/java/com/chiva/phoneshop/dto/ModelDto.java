package com.chiva.phoneshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ModelDto {

    private Integer id;

    private String name;

    @JsonProperty("brand_id")
    private Integer brandId;
}
