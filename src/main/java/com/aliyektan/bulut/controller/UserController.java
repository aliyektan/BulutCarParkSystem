package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.dto.Response;
import com.aliyektan.bulut.dto.UserDTO;
import com.aliyektan.bulut.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping
    public Response getAll() {

        return Response
                .builder()
                .data(userService.findAll())
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @GetMapping(path = "/{id}")
    public Response getOne(@PathVariable("id") Integer id) {

        return Response
                .builder()
                .data(userService.findById(id))
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @PostMapping
    public Response create(@RequestBody UserDTO userDTO) {
        return Response
                .builder()
                .data(userService.create(userDTO))
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

    @ResponseBody
    @PutMapping
    public Response update(@RequestBody UserDTO userDTO) {
        return Response
                .builder()
                .data(userService.save(userDTO))
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

    @ResponseBody
    @DeleteMapping(path = "/{id}")
    public Response delete(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return Response
                .builder()
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

}
