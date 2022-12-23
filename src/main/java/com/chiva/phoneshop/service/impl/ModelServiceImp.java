package com.chiva.phoneshop.service.impl;

import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.model.Model;
import com.chiva.phoneshop.repository.ModelRepository;
import com.chiva.phoneshop.service.BrandService;
import com.chiva.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        Brand brand = this.brandService.getById(model.getBrand().getId());
        return modelRepository.save(model);
    }
}
