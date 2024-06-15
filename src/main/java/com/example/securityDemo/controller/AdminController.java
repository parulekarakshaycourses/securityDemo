package com.example.securityDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class AdminController
{
    @GetMapping("/page1/")
    public String adminPage1()
    {
        return "adminPage1";
    }

    @GetMapping("/page2/")
    public String adminPage2()
    {
        return "adminPage2";
    }
}
