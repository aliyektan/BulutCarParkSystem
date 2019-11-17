package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.service.base.ParkingEventService;
import com.aliyektan.bulut.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/parking-events")
public class ParkingEventController {

    @Autowired
    private ParkingEventService parkingEventService;

}
