package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.dto.BranchDTO;
import com.aliyektan.bulut.dto.Response;
import com.aliyektan.bulut.service.base.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @ResponseBody
    @GetMapping
    public Response getAll() {

        return Response
                .builder()
                .data(branchService.findAll())
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @GetMapping(path = "/{id}")
    public Response getOne(@PathVariable("id") Integer id) {

        return Response
                .builder()
                .data(branchService.findById(id))
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @PostMapping
    public Response save(@RequestBody BranchDTO dto) {
        return Response
                .builder()
                .data(branchService.save(dto))
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

    @ResponseBody
    @DeleteMapping(path = "/{id}")
    public Response delete(@PathVariable("id") Integer id) {
        branchService.deleteById(id);
        return Response
                .builder()
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

}
