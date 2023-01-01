package com.chiva.phoneshop.repository;

import com.chiva.phoneshop.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand> {
    boolean existsByNameIgnoreCase(String name);

    List<Brand> findByIdIn(List<Integer> ids);
}
