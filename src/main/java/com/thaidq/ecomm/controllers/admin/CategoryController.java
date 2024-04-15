package com.thaidq.ecomm.controllers.admin;

import com.thaidq.ecomm.models.Category;
import com.thaidq.ecomm.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RequestMapping("/admin/category")
@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        List<Category> list = categoryService.getAll();
        model.addAttribute("categories", list);
        return "admin/category/index";
    }

    @GetMapping("/add")
    public String addNewCategory(Model model) {
        Category category = new Category();
        category.setCategoryStatus(true);
        model.addAttribute("category", category);
        return "admin/category/add-category";
    }

    @PostMapping("/save")
    public String save(Category category, RedirectAttributes ra) {
        if (categoryService.create(category)) {
            return "redirect:/admin/category";

        } else {
            ra.addFlashAttribute("message", "Failed");
            return "redirect:/admin/category/add";

        }
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model, RedirectAttributes ra) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isEmpty()) {
            ra.addFlashAttribute("message", "Category not found!");
        } else {
            model.addAttribute("category", category.get());
        }
        return "admin/category/add-category";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra){
        if(categoryService.delete(id)){
            ra.addFlashAttribute("message", "Deleted successful!");
        }else{
            ra.addFlashAttribute("message", "Failed to delete category!");
        }
        return "redirect:/admin/category";
    }
}
