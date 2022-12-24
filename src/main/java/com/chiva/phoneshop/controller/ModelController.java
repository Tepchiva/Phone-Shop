package com.chiva.phoneshop.controller;

import com.chiva.phoneshop.dto.ModelDto;
import com.chiva.phoneshop.dto.PageDto;
import com.chiva.phoneshop.mapper.ModelMapper;
import com.chiva.phoneshop.mapper.PageMapper;
import com.chiva.phoneshop.model.Model;
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
    @PostMapping
    public ResponseEntity<ModelDto> create(@RequestBody ModelDto modelDto) {

        Model model = ModelMapper.INSTANCE.toModel(modelDto);

        return ResponseEntity.ok(ModelMapper.INSTANCE.toModelDto(modelService.save(model)));
    }

    @GetMapping( path = "{id}")
    public ResponseEntity<ModelDto> getById(@PathVariable("id") int id) {
        log.info("get model by id: %d".formatted(id));
        Model model = modelService.getById(id);
        return ResponseEntity.ok(ModelMapper.INSTANCE.toModelDto(model));
    }

    @GetMapping()
    public ResponseEntity<List<ModelDto>> getModels(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(modelService.getModels(params).stream().map(ModelMapper.INSTANCE::toModelDto).toList());
    }

    @GetMapping(path = "pagination")
    public ResponseEntity<PageDto> getModelWithPagination(@RequestParam Map<String, String> params) {

        Page<Model> page = modelService.getModelWithPagination(params);

        // Use PageMapper instead
        // PageDto pageDto = new PageDto(page);

        PageDto pageDto = PageMapper.INSTANCE.toPageDto(page);
        pageDto.setList(page.stream().map(ModelMapper.INSTANCE::toModelDto).toList());

        return ResponseEntity.ok(pageDto);
    }
}
