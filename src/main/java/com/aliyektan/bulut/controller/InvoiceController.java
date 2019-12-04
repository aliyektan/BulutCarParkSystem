package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.dto.LicenseNumberDTO;
import com.aliyektan.bulut.dto.Response;
import com.aliyektan.bulut.service.base.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @ResponseBody
    @GetMapping
    public Response getAll() {

        return Response
                .builder()
                .data(invoiceService.findAll())
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @GetMapping(path = "/{id}")
    public Response getOne(@PathVariable("id") Integer id) {

        return Response
                .builder()
                .data(invoiceService.findById(id))
                .httpStatus(HttpStatus.OK.value())
                .build();

    }

    @ResponseBody
    @PostMapping(path = "/bill")
    public Response start(@RequestBody LicenseNumberDTO dto) {
        return Response
                .builder()
                .data(invoiceService.bill(dto))
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

}
