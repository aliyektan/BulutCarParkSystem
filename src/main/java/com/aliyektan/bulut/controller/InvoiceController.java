package com.aliyektan.bulut.controller;

import com.aliyektan.bulut.service.base.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

}
