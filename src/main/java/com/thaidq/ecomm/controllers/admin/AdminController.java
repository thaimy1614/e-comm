package com.thaidq.ecomm.controllers.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @GetMapping("")
    public String index(){
        return "redirect:/admin/";
    }

    @GetMapping("/")
    public String admin(){
        return "admin/index";
    }
}
