package com.thaidq.ecomm.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/category")
@Controller
public class CategoryController {
    @GetMapping
    public String index(){
        return "admin/category/index";
    }

    @GetMapping("/add")
    public String addNewCategory(){
        return "admin/category/add-category";
    }
}
