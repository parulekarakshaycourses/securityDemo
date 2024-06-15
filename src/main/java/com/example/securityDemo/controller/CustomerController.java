package com.example.securityDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/")
public class CustomerController
{
    @GetMapping("/page1/")
    public String customerPage1()
    {
        return "customerPage1";
    }

    @GetMapping("/page2/")
    public String customerPage2()
    {
        return "customerPage2";
    }
}