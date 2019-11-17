package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.service.base.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

}
