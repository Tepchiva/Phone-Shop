package com.chiva.phoneshop.controller;

import com.chiva.phoneshop.dto.ModelDto;
import com.chiva.phoneshop.mapper.BrandMapper;
import com.chiva.phoneshop.mapper.ModelMapper;
import com.chiva.phoneshop.model.Model;
import com.chiva.phoneshop.service.BrandService;
import com.chiva.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/models")
@RequiredArgsConstructor
@Slf4j
public class ModelController {
    private final ModelService modelService;
    @PostMapping
    public ResponseEntity<ModelDto> create(@RequestBody ModelDto modelDto) {

        Model model = ModelMapper.INSTANCE.toModel(modelDto);

        return ResponseEntity.ok(ModelMapper.INSTANCE.toModelDto(modelService.save(model)));
    }
}
