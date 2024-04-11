package com.thaidq.ecomm.controllers.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @GetMapping
    public String index(){
        return "redirect:/admin/";
    }
    @GetMapping("/") // Handles both /admin and /admin/
    public String admin() {
        return "admin/index";
    }
}
