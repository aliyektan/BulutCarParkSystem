package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.dto.Response;
import com.aliyektan.bulut.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping(path = "/info")
    public Response getAccountInfo() {

        return Response
                .builder()
                .data(userService.getAuthenticatedUserInfo())
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

}
