package com.chiva.phoneshop.spec;

import com.chiva.phoneshop.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class BrandSpecification implements Specification<Brand> {

    private Integer brandId;
    private String brandName;

    @Override
    public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder cb) {

        List<Predicate> predicateList = new ArrayList<>();
        if (brandId != null) predicateList.add(brand.get("id").in(brandId));
        if (brandName !=null) predicateList.add(cb.like(cb.upper(brand.get("name")), "%"+brandName.toUpperCase()+"%"));

        Predicate[] predicates = predicateList.toArray(Predicate[]::new);

        return cb.and(predicates);
    }
}
