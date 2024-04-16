package com.thaidq.ecomm.controllers.admin;

import com.thaidq.ecomm.models.Category;
import com.thaidq.ecomm.models.Product;
import com.thaidq.ecomm.repository.CategoryRepository;
import com.thaidq.ecomm.services.CategoryService;
import com.thaidq.ecomm.services.FileSystemStorageService;
import com.thaidq.ecomm.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    private final FileSystemStorageService fileSystemStorageService = new FileSystemStorageService();

    @GetMapping("")
    public String index(Model model) {

        List<Product> productList = productService.getAll();
        model.addAttribute("products", productList);
        return "admin/product/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Product product = new Product();
        product.setProductStatus(true);

        List<Category> categoryList = categoryService.getAll(true);
        model.addAttribute("categories", categoryList);
        model.addAttribute("product", product);
        return "admin/product/add-product";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("product") Product product,
                       @RequestParam("product-img") MultipartFile file,
                       BindingResult result,
                       RedirectAttributes ra) {
        if (result.hasErrors()) {
            ra.addFlashAttribute("message", "Failed validation");
            return "redirect:/admin/product/add";
        }

        try {
            // Store the uploaded file and get the filename
            String fileName = fileSystemStorageService.store(file);

            // Set the filename to the image property of the product
            product.setProductImage(fileName);

            // Save the product
            if (productService.create(product)) {
                return "redirect:/admin/product";
            } else {
                ra.addFlashAttribute("message", "Failed to save product");
                return "redirect:/admin/product/add";
            }
        } catch (IOException e) {
            e.printStackTrace();
            ra.addFlashAttribute("message", "Failed to upload image");
            return "redirect:/admin/product/add";
        }
    }


    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model, RedirectAttributes ra) {
        Optional<Product> product = productService.findById(id);
        if (product.isEmpty()) {
            ra.addFlashAttribute("message", "Product not found!");
        } else {
            model.addAttribute("product", product.get());
            List<Category> categoryList = categoryService.getAll(true);
            model.addAttribute("categories", categoryList);
        }
        return "admin/product/add-product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra){
        if(productService.delete(id)){
            ra.addFlashAttribute("message", "Deleted successful!");
        }else{
            ra.addFlashAttribute("message", "Failed to delete product!");
        }
        return "redirect:/admin/product";
    }
}
