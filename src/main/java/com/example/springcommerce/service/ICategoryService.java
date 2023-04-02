package com.example.springcommerce.service;
import com.example.springcommerce.model.Category;
import java.util.List;
import java.util.Optional;


public interface ICategoryService {
    List<Category> getAllCategories();
    Optional<Category> findById(Integer id);
}
