package com.thaidq.ecomm.controllers.admin;

import com.thaidq.ecomm.models.Category;
import com.thaidq.ecomm.services.CategoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@RequestMapping("/admin/category")
@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;




    @GetMapping
    public String index(Model model) {
        List<Category> categoryList = categoryService.getAll();
        int size = categoryList.size();
        Page<Category> list = categoryService.getAll(1);
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("currentPage", 1);
        model.addAttribute("categories", list);
        return "admin/category/index";
    }

    @GetMapping("/{page}")
    public String index(Model model, @PathVariable int page){
        Page<Category> list = categoryService.getAll(page);
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("currentPage", page);
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

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model){
        List<Category> list = categoryService.getAll();
        if(keyword!=null){
            list = categoryService.searchCategory(keyword);
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("categories", list);
        return "admin/category/index";
    }
}
