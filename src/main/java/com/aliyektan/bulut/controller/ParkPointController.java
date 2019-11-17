package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.service.base.ParkPointService;
import com.aliyektan.bulut.service.base.ParkingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/park-points")
public class ParkPointController {

    @Autowired
    private ParkPointService parkPointService;

}
