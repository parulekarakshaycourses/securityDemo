package com.example.securityDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marketer/")
public class MarketerController
{
    @GetMapping("/page1/")
    public String marketerPage1()
    {
        return "marketerPage1";
    }

    @GetMapping("/page2/")
    public String marketerPage2()
    {
        return "marketerPage2";
    }
}