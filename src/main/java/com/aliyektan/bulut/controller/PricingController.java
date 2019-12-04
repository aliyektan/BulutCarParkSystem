package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.dto.PricingDTO;
import com.aliyektan.bulut.dto.Response;
import com.aliyektan.bulut.service.base.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pricing")
public class PricingController {

    @Autowired
    private PricingService pricingService;

    @ResponseBody
    @GetMapping
    public Response getAll() {

        return Response
                .builder()
                .data(pricingService.findAll())
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @GetMapping(path = "/{id}")
    public Response getOne(@PathVariable("id") Integer id) {

        return Response
                .builder()
                .data(pricingService.findById(id))
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @PostMapping
    public Response save(@RequestBody PricingDTO dto) {
        return Response
                .builder()
                .data(pricingService.save(dto))
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

    @ResponseBody
    @DeleteMapping(path = "/{id}")
    public Response delete(@PathVariable("id") Integer id) {
        pricingService.deleteById(id);
        return Response
                .builder()
                .httpStatus(HttpStatus.OK.value())
                .build();
    }
    
}
