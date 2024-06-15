package com.example.securityDemo.controller;

import com.example.securityDemo.entity.Employee;
import com.example.securityDemo.repo.EmpRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController
{
    @Autowired
    EmpRepo empRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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

    @PostMapping("/emp/save/")
    public String saveEmp(Employee emp)
    {
        System.out.println("Employee : " + emp.toString());
        emp.setIdRole(2);
        emp.setPassword(encoder.encode(emp.getPassword()));
        empRepo.save(emp);
        return "redirect:/customLogin/";
    }

    @GetMapping("/regcust/")
    public String regCust()
    {
        return "regCustomer";
    }
}
