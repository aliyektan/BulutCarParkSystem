package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.dto.NewPasswordDTO;
import com.aliyektan.bulut.dto.Response;
import com.aliyektan.bulut.service.base.AccountService;
import com.aliyektan.bulut.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @ResponseBody
    @GetMapping(path = "/info")
    public Response getAccountInfo() {

        return Response
                .builder()
                .data(userService.getAuthenticatedUserInfo())
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @PostMapping(path = "/password")
    public Response changePassword(@RequestBody NewPasswordDTO newPasswordDTO) {

        return Response
                .builder()
                .data(accountService.changePassword(newPasswordDTO))
                .httpStatus(HttpStatus.OK.value())
                .build();

    }


}
