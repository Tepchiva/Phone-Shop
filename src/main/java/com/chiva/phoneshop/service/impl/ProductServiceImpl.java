package com.chiva.phoneshop.service.impl;

import com.chiva.phoneshop.model.Product;
import com.chiva.phoneshop.repository.ProductRepository;
import com.chiva.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
