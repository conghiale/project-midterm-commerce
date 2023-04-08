package com.example.springcommerce.service;

import com.example.springcommerce.model.Category;
import com.example.springcommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
//    public List<Product> selectAll();
    Page<Product> selectAll(Pageable pageable);
    public Product selectById(int id);
    public Product insert(Product product);
    public boolean deleteById(int id);
    public Product update(int id, Product productUpdate);
//    Iterable<Product> findAllByCategory(Category category);
}
