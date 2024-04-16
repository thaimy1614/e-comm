package com.thaidq.ecomm.services;


import com.thaidq.ecomm.models.Product;
import com.thaidq.ecomm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductByName(String productName){
        return productRepository.findByProductName(productName);
    }

    public boolean create(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public boolean update(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        Optional<Product> category = productRepository.findById(id);

        try {
            if(category.isPresent()){
                String pName = category.get().getProductName();
                Product newProduct = new Product(id,pName,category.get().getPrice(), category.get().getProductImage(),category.get().getDescription(),false, category.get().getCategory());
                productRepository.save(newProduct);
                return true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

}
