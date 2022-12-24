package com.chiva.phoneshop.service;

import com.chiva.phoneshop.exception.ApiException;
import com.chiva.phoneshop.model.Model;

public interface ModelService {

    Model save(Model model);

    Model getById(Integer id);
}