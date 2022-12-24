package com.chiva.phoneshop.spec;

import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.model.Model;
import com.chiva.phoneshop.utils.ModelFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ModelSpecification implements Specification<Model> {
    private final ModelFilter modelFilter;

    @Override
    public Predicate toPredicate(Root<Model> model, CriteriaQuery<?> query, CriteriaBuilder cb) {

        List<Predicate> predicateList = new ArrayList<>();
        Join<Model, Brand> brandJoin = model.join("brand");

        if (modelFilter.getModelId() != null)
            // use where id
            // predicateList.add(model.get("id").in(modelFilter.getId()));
            predicateList.add(cb.equal(model.get("id"), modelFilter.getModelId()));

        if (modelFilter.getModelName() != null)
            predicateList.add(cb.like(cb.upper(model.get("name")), "%" + modelFilter.getModelName().toUpperCase() + "%"));

        if (modelFilter.getBrandId() != null) predicateList.add(cb.equal(brandJoin.get("id"), modelFilter.getBrandId()));

        if (modelFilter.getBrandName() != null) {
            Predicate predicateBrandName = cb.like(cb.upper(brandJoin.get("name")), "%" + modelFilter.getBrandName().toUpperCase() + "%");
            predicateList.add(predicateBrandName);
        }

        Predicate[] predicates = predicateList.toArray(/*new Predicate[]{}*/Predicate[]::new);

        return cb.and(predicates);
    }
}
