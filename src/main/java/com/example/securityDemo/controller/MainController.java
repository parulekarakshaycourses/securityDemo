package com.example.securityDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController
{
    @GetMapping("/page1/")
    public String publicPage1()
    {
        return "publicPage1";
    }

    @GetMapping("/page2/")
    public String publicPage2()
    {
        return "publicPage2";
    }
}
