package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.dto.LicenseNumberDTO;
import com.aliyektan.bulut.dto.Response;
import com.aliyektan.bulut.service.base.ParkingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/parking-events")
public class ParkingEventController {

    @Autowired
    private ParkingEventService parkingEventService;

    @ResponseBody
    @GetMapping
    public Response getAll() {

        return Response
                .builder()
                .data(parkingEventService.findAll())
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @GetMapping(path = "/{id}")
    public Response getOne(@PathVariable("id") Integer id) {

        return Response
                .builder()
                .data(parkingEventService.findById(id))
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @PostMapping(path = "/start")
    public Response start(@RequestBody LicenseNumberDTO dto) throws Exception {
        return Response
                .builder()
                .data(parkingEventService.startParking(dto))
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

    @ResponseBody
    @PostMapping(path = "/active")
    public Response getActive(@RequestBody LicenseNumberDTO dto) {
        return Response
                .builder()
                .data(parkingEventService.getActiveParkingEvent(dto))
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

}
