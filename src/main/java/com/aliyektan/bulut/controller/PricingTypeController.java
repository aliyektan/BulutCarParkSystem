package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.dto.PricingTypeDTO;
import com.aliyektan.bulut.dto.Response;
import com.aliyektan.bulut.service.base.PricingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pricing-types")
public class PricingTypeController {

    @Autowired
    private PricingTypeService pricingTypeService;

    @ResponseBody
    @GetMapping
    public Response getAll() {

        return Response
                .builder()
                .data(pricingTypeService.findAll())
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @GetMapping(path = "/{id}")
    public Response getOne(@PathVariable("id") Integer id) {

        return Response
                .builder()
                .data(pricingTypeService.findById(id))
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @PostMapping
    public Response save(@RequestBody PricingTypeDTO dto) {
        return Response
                .builder()
                .data(pricingTypeService.save(dto))
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

    @ResponseBody
    @DeleteMapping(path = "/{id}")
    public Response delete(@PathVariable("id") Integer id) {
        pricingTypeService.deleteById(id);
        return Response
                .builder()
                .httpStatus(HttpStatus.OK.value())
                .build();
    }
    
}
