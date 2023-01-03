package com.chiva.phoneshop.controller;

import com.chiva.phoneshop.dto.ProductDTO;
import com.chiva.phoneshop.mapper.ProductMapper;
import com.chiva.phoneshop.model.Product;
import com.chiva.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        log.info("Intercept product dto: {}", productDTO);
        Product product = productService.save(productMapper.toProduct(productDTO));
        log.info("Product after created: {}", product);
        return ResponseEntity.ok(productMapper.toProductDTO(product));
    }
}
