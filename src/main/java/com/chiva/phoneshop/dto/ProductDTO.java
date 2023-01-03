package com.chiva.phoneshop.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    @JsonProperty("model_id")
    private Integer modelId;

    @JsonProperty("year_made")
    private Short yearMade;

    /*
    @JsonProperty("color_id")
    private Integer color;
     */

    @JsonProperty("import_price")
    private BigDecimal importPrice;

    @JsonProperty("sale_price")
    private BigDecimal salePrice;

    @JsonProperty("import_date")
    private LocalDateTime importDate;

    private Integer qty;

    @JsonProperty("image_url")
    private String imageUrl;
}
