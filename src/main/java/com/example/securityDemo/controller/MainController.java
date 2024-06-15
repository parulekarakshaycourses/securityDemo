package com.example.securityDemo.controller;

import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("/customLogin/")
    public String customLogin()
    {
        return "customLoginPage";
    }

    @GetMapping("/dashboard/")
    public String dashboard(HttpServletRequest request)
    {
        if(request.isUserInRole("ROLE_ADMIN"))
        {
            return "redirect:/admin/page1/";
        }
        else if(request.isUserInRole("ROLE_MARKETER"))
        {
            return "redirect:/marketer/page1/";
        }
        else if(request.isUserInRole("ROLE_CUSTOMER"))
        {
            return "redirect:/customer/page1/";
        }
        return "redirect:/customLogin/";
    }
}
