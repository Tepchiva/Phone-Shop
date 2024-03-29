package com.chiva.phoneshop.spec;

import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.model.Brand_;
import com.chiva.phoneshop.utils.Constant;
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
@RequiredArgsConstructor
public class BrandSpecification implements Specification<Brand> {

    private final Integer brandId;
    private final String brandName;

    private String status;

    @Override
    public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder cb) {

        List<Predicate> predicateList = new ArrayList<>();
        if (brandId != null) predicateList.add(brand.get(Brand_.ID).in(brandId));
        if (status != null) predicateList.add(brand.get(Brand_.STATUS).in(status.toUpperCase()));
        else predicateList.add(cb.notEqual(cb.upper(brand.get(Brand_.STATUS)), Constant.STATUS_DEL));
        if (brandName !=null) predicateList.add(cb.like(cb.upper(brand.get(Brand_.NAME)), "%"+brandName.toUpperCase()+"%"));

        Predicate[] predicates = predicateList.toArray(Predicate[]::new);

        return cb.and(predicates);
    }
}
