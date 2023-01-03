package com.chiva.phoneshop.repository;

import com.chiva.phoneshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
