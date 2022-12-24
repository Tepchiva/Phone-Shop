package com.chiva.phoneshop.service.impl;

import com.chiva.phoneshop.exception.ResourceNotFoundException;
import com.chiva.phoneshop.model.Model;
import com.chiva.phoneshop.repository.ModelRepository;
import com.chiva.phoneshop.service.BrandService;
import com.chiva.phoneshop.service.ModelService;
import com.chiva.phoneshop.utils.ModelFilter;
import com.chiva.phoneshop.spec.ModelSpecification;
import com.chiva.phoneshop.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ModelServiceImp implements ModelService {

    private final ModelRepository modelRepository;

    private final BrandService brandService;

    /*replace by @RequiredArgsConstructor annotation*/
    //    public ModelServiceImp(ModelRepository modelRepository) {
    //        this.modelRepository = modelRepository;
    //    }

    @Override
    public Model save(Model model) {

        // check brand
        this.brandService.getById(model.getBrand().getId());
        return modelRepository.save(model);
    }

    @Override
    public Model getById(Integer id) {
        return modelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model", id));
    }

    @Override
    public List<Model> getModels(Map<String, String> params) {

        /*
        * anonymous object
        *
        Specification<Model> specification = new Specification<Model>() {
            final String modelName = params.getOrDefault("name", null);
            @Override
            public Predicate toPredicate(Root<Model> model, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (this.modelName != null) {
                    Predicate predicateName = cb.like(cb.upper(model.get("name")), "%"+modelName.toUpperCase()+"%");
                    return predicateName;
                }
                return null;
            }
        };
         */

        /*
        * Use lambda expression
        * option param type
        *
        Specification<Model> specification = (model, query, cb) -> {
            String modelName = params.getOrDefault("name", null);
            if (modelName != null) {
                Predicate predicateName = cb.like(cb.upper(model.get("name")), "%"+modelName.toUpperCase()+"%");
                return predicateName;
            }
            return null;
        };
         */

        List<Model> models = modelRepository.findAll(getModelSpecification(params), Sort.by(Sort.Order.asc("id")));
        return models;
    }

    @Override
    public Page<Model> getModelWithPagination(Map<String, String> params) {
        return modelRepository.findAll(getModelSpecification(params), PageUtils.getPageable(params));
    }

    private ModelSpecification getModelSpecification(Map<String, String> params) {
        ModelFilter modelFilter = new ModelFilter();
        modelFilter.setModelId(MapUtils.getInteger(params, "modelId",null));
        modelFilter.setModelName(params.getOrDefault("modelName", null));
        modelFilter.setBrandId(MapUtils.getInteger(params, "brandId",null));
        modelFilter.setBrandName(params.getOrDefault("brandName", null));

        return new ModelSpecification(modelFilter);
    }
}
