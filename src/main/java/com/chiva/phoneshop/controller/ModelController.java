package com.chiva.phoneshop.controller;

import com.chiva.phoneshop.dto.ModelDTO;
import com.chiva.phoneshop.dto.PageDTO;
import com.chiva.phoneshop.exception.SuccessResponse;
import com.chiva.phoneshop.mapper.ModelMapper;
import com.chiva.phoneshop.mapper.PageMapper;
import com.chiva.phoneshop.model.Model;
import com.chiva.phoneshop.service.MessageResponseService;
import com.chiva.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/models")
@RequiredArgsConstructor
@Slf4j
public class ModelController {
    private final ModelService modelService;
    private final MessageResponseService messageResponseService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<SuccessResponse<ModelDTO>> create(@RequestBody ModelDTO modelDto) {
        // check brand
        // already check in ModelMapper
        // this.brandService.getById(modelDto.getBrandId());

        // if we try use '@Mapper(componentModel = "spring", uses = {BrandService.class})' in mpper,
        // 'ModelMapper.INSTANCE.toModel(modelDto)' will not support with autowire of spring
        // because it's static, it's lost context of spring
        // Model model = ModelMapper.INSTANCE.toModel(modelDto);
         Model model = this.modelMapper.toModel(modelDto);
        ModelDTO resModel = ModelMapper.INSTANCE.toModelDto(modelService.save(model));
        return messageResponseService.handleSuccessMsgResponse(resModel);
    }

    @GetMapping( path = "{id}")
    public ResponseEntity<ModelDTO> getById(@PathVariable("id") int id) {
        log.info("get model by id: %d".formatted(id));
        Model model = modelService.getById(id);
        return ResponseEntity.ok(ModelMapper.INSTANCE.toModelDto(model));
    }

    @GetMapping()
    public ResponseEntity<List<ModelDTO>> getModels(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(modelService.getModels(params).stream().map(ModelMapper.INSTANCE::toModelDto).toList());
    }

    @GetMapping(path = "pagination")
    public ResponseEntity<PageDTO> getModelWithPagination(@RequestParam Map<String, String> params) {

        Page<Model> page = modelService.getModelWithPagination(params);

        // Use PageMapper instead
        // PageDto pageDto = new PageDto(page);

        PageDTO pageDto = PageMapper.INSTANCE.toPageDto(page);
        pageDto.setList(page.stream().map(ModelMapper.INSTANCE::toModelDto).toList());

        return ResponseEntity.ok(pageDto);
    }
}
