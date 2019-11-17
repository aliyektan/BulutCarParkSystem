package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.service.base.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/pricing")
public class PricingController {

    @Autowired
    private PricingService pricingService;

}
