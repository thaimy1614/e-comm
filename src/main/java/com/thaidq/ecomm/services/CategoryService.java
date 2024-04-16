package com.thaidq.ecomm.services;

import com.thaidq.ecomm.models.Category;
import com.thaidq.ecomm.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public List<Category> getAll(boolean status) {
        return categoryRepository.findByCategoryStatus(true);
    }

    public boolean create(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }

    public boolean update(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        Optional<Category> category = categoryRepository.findById(id);

        try {
            if (category.isPresent()) {
                String cName = category.get().getCategoryName();
                Category newCategory = new Category(id, cName, false, null);
                categoryRepository.save(newCategory);
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Category> searchCategory(String categoryName){
        return categoryRepository.searchCategory(categoryName);
    }
}
