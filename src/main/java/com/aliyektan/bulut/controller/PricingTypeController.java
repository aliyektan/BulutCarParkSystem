package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.service.base.PricingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/pricing-types")
public class PricingTypeController {

    @Autowired
    private PricingTypeService pricingTypeService;

}
