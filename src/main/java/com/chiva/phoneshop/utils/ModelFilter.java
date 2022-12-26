package com.chiva.phoneshop.utils;

import lombok.Data;

@Data
public class ModelFilter {
    private Integer modelId;
    private String modelName;
    private String brandName;
    private Integer brandId;
}
