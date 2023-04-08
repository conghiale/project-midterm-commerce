package com.example.springcommerce.service;

import com.example.springcommerce.model.Category;
import com.example.springcommerce.model.Product;
import com.example.springcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService implements IProductService{
    @Autowired
    private ProductRepository productRepository;

    //Thay List thành Page và thêm tham số Pageable trong selectAll. Sửa bên IProductService luôn nha e
    @Override
    public Page<Product> selectAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    @Override
    public Product selectById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    @Override
    public Product insert(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean deleteById(int id) {
        if (productRepository.existsById(id))
            productRepository.deleteById(id);
        return !productRepository.existsById(id);
    }

    @Override
    public Product update(int id, Product objectUpdate) {
        return null;
    }

//    @Override
//    public Iterable<Product> findAllByCategory(Category category) {
//        Iterable<Product> products= productRepository.findAllByCategory(category);
//        return products;
//    }

//    @Override
//    public Cart update(int id, Cart cartUpdate) {
//        Cart cart = selectById(id);
//        cart.setQuantity(cartUpdate.getQuantity());
//        cart.setCost(cartUpdate.getCost());
//        cart.setProductId(cartUpdate.getProductId());
//        return cartRepository.save(cart);
//    }


}
