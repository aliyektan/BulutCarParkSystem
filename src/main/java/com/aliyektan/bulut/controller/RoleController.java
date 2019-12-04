package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.dto.Response;
import com.aliyektan.bulut.service.base.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @GetMapping
    public Response getAll() {

        return Response
                .builder()
                .data(roleService.findAll())
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @GetMapping(path = "/{id}")
    public Response getOne(@PathVariable("id") Integer id) {

        return Response
                .builder()
                .data(roleService.findById(id))
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

}
