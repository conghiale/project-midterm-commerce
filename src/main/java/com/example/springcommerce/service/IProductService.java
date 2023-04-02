package com.example.springcommerce.service;

import com.example.springcommerce.model.Category;
import com.example.springcommerce.model.Product;

import java.util.List;

public interface IProductService {
    public List<Product> selectAll();
    public Product selectById(int id);
    public Product insert(Product product);
    public boolean deleteById(int id);
    public Product update(int id, Product productUpdate);
//    List<Product> findAllByCategory(Category category);
}
