package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.dto.PricingPeriodDTO;
import com.aliyektan.bulut.dto.Response;
import com.aliyektan.bulut.service.base.PricingPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pricing-types")
public class PricingPeriodController {

    @Autowired
    private PricingPeriodService pricingPeriodService;

    @ResponseBody
    @GetMapping
    public Response getAll() {

        return Response
                .builder()
                .data(pricingPeriodService.findAll())
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @GetMapping(path = "/{id}")
    public Response getOne(@PathVariable("id") Integer id) {

        return Response
                .builder()
                .data(pricingPeriodService.findById(id))
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @PostMapping
    public Response save(@RequestBody PricingPeriodDTO dto) {
        return Response
                .builder()
                .data(pricingPeriodService.save(dto))
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

    @ResponseBody
    @DeleteMapping(path = "/{id}")
    public Response delete(@PathVariable("id") Integer id) {
        pricingPeriodService.deleteById(id);
        return Response
                .builder()
                .httpStatus(HttpStatus.OK.value())
                .build();
    }
    
}
