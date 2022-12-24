package com.chiva.phoneshop.service;

import com.chiva.phoneshop.exception.ApiException;
import com.chiva.phoneshop.model.Model;

public interface ModelService {

    Model save(Model model) throws ApiException;

    Model getById(Integer id) throws ApiException;
}