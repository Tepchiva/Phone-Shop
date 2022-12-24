package com.chiva.phoneshop.service;

import com.chiva.phoneshop.exception.ApiException;
import com.chiva.phoneshop.model.Model;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ModelService {

    Model save(Model model);

    Model getById(Integer id);

    List<Model> getModels(Map<String, String> params);

    Page<Model> getModelWithPagination(Map<String, String> params);
}