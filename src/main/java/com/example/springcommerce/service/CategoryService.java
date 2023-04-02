package com.example.springcommerce.service;

import com.example.springcommerce.model.Category;
import com.example.springcommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories != null){
            return categories;
        }
        return null;
    }

    public Optional<Category> findById(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        return category;
    }
}
