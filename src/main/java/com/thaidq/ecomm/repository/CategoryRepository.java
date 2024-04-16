package com.thaidq.ecomm.repository;

import com.thaidq.ecomm.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByCategoryName(String categoryName);

    List<Category> searchCategoriesByCategoryName(String categoryName);

    @Query("Select c from Category c where c.categoryName LIKE %?1% ")
    List<Category> searchCategory(String categoryName);

    List<Category> findByCategoryStatus(boolean status);
}
