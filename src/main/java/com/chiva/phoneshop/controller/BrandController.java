package com.chiva.phoneshop.controller;

import com.chiva.phoneshop.dto.BrandDto;
import com.chiva.phoneshop.mapper.EntityMapper;
import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping(path = "/brands")
public class BrandController {
    private final BrandService brandService;
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping()
    public ResponseEntity<Brand> create(@RequestBody BrandDto brandDto) {
        Brand brand = EntityMapper.toBrand(brandDto);
        brand = brandService.save(brand);
        return ResponseEntity.ok(brand);
    }

    @GetMapping
    public ResponseEntity<List<BrandDto>> getAllBrands() {

        List<Brand> allBrands = brandService.getAllBrands();

        Stream<BrandDto> brandDtoStream = allBrands.stream().map(brand -> EntityMapper.toBrandDto(brand));
        // List<BrandDto> allBrandDto = brandDtoStream.collect(Collectors.toList());
        List<BrandDto> allBrandDto = brandDtoStream.toList(); // java >= 16

        return ResponseEntity.ok(allBrandDto);
    }

    @GetMapping( path = "{id}")
    public ResponseEntity<Brand> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok(brandService.getById(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Brand> update(@PathVariable int id, @RequestBody BrandDto brandDto) {

        return ResponseEntity.ok(this.brandService.update(id, brandDto));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        brandService.delete(id);
        log.info("delete record id: %d".formatted(id));
        return ResponseEntity.ok().build();
    }
}
