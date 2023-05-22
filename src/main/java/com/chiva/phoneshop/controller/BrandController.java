package com.chiva.phoneshop.controller;

import com.chiva.phoneshop.dto.BrandDTO;
import com.chiva.phoneshop.exception.SuccessResponse;
import com.chiva.phoneshop.mapper.BrandMapper;
import com.chiva.phoneshop.model.Brand;
import com.chiva.phoneshop.service.BrandService;
import com.chiva.phoneshop.service.MessageResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping(path = "/brands")
//@RequiredArgsConstructor
public class BrandController {

    @Autowired
    private MessageResponseService messageResponseService;

    @Autowired
    private BrandService brandService;

    //    public BrandController(BrandService brandService) {
    //        this.brandService = brandService;
    //    }

    @PostMapping()
    public ResponseEntity<SuccessResponse<Brand>> create(@RequestBody BrandDTO brandDto) {
        // Brand brand = EntityMapper.toBrand(brandDto);
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDto);
        return messageResponseService.handleSuccessMsgResponse(brandService.save(brand));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<BrandDTO>>> getAllBrands(@RequestParam Map<String, String> params) {

        List<Brand> brands = brandService.getBrands(params);

        // List<BrandDto> allBrandDto = brandDtoStream.collect(Collectors.toList());

        Stream<BrandDTO> brandDtoStream = brands.stream().map(BrandMapper.INSTANCE::toBrandDto /*EntityMapper.toBrandDto(brand)*/);

        List<BrandDTO> allBrandDto = brandDtoStream.toList(); // java >= 16

        return messageResponseService.handleSuccessMsgResponse(allBrandDto);
    }

    @GetMapping( path = "{id}")
    public ResponseEntity<SuccessResponse<Brand>> getById(@PathVariable("id") int id) {
        log.info("get brand by id: %d".formatted(id));
        return messageResponseService.handleSuccessMsgResponse(brandService.getById(id));
    }

    // old
    //    @PutMapping(path = "{id}")
    //    public ResponseEntity<Brand> update(@PathVariable int id, @RequestBody BrandDto brandDto) {
    //
    //        return ResponseEntity.ok(this.brandService.update(id, brandDto));
    //    }
    //
    @PutMapping(path = "{id}")
    public ResponseEntity<SuccessResponse<Brand>> update(@PathVariable int id, @RequestBody BrandDTO brandDto) {
        return messageResponseService.handleSuccessMsgResponse(this.brandService.update(id, BrandMapper.INSTANCE.toBrand(brandDto)));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        brandService.delete(id);
        log.info("delete record id: %d".formatted(id));
        return messageResponseService.handleSuccessMsgResponse(null);
    }
}
