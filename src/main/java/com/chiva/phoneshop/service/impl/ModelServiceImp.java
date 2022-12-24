package com.chiva.phoneshop.service.impl;

import com.chiva.phoneshop.exception.ApiException;
import com.chiva.phoneshop.exception.ResourceNotFoundException;
import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.model.Model;
import com.chiva.phoneshop.repository.ModelRepository;
import com.chiva.phoneshop.service.BrandService;
import com.chiva.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
