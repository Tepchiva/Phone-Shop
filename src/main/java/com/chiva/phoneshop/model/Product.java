package com.chiva.phoneshop.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "{can't.blank}")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "{can't.null}")
    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @NotNull(message = "{can't.null}")
    @Column(name = "year_made", nullable = false)
    private Short yearMade;

    /*
    @NotNull(message = "{can't.null}")
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
    */

    @DecimalMin(value = "0.1")
    @Column(name = "import_price", nullable = false)
    private BigDecimal importPrice;

    @Column(nullable = false, name = "sale_price")
    private BigDecimal salePrice;

    @Column(nullable = false, name = "import_date")
    private LocalDateTime importDate;

    //@Should move to stock table
    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = true, name = "image_url")
    private String imageUrl;
}
