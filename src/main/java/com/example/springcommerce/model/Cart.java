package com.example.springcommerce.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product,Integer> products = new HashMap<>();

    public Cart() {
    }

    public Cart(Map<Product,Integer> products) {
        this.products = products;
    }

    public Map<Product,Integer> getProducts() {
        return products;
    }

    //checkIntemInCart() để kiểm tra xem sản phẩm đó đã có trong giỏ hàng hay chưa
    private boolean checkItemInCart(Product product){
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if(entry.getKey().getId() == product.getId()){
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Product, Integer> selectItemInCart(Product product){
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if(entry.getKey().getId() == product.getId()){
                return entry;
            }
        }
        return null;
    }

    public Map<Product, Integer> deleteProduct(Product product){
        if(!products.isEmpty()) {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                if (entry.getKey().getId() == product.getId()) {
                    products.remove(entry.getKey());
                }
            }
            return products;
        }
        return null;
    }

    //addProduct() được sử dụng để thêm sản phẩm vào trong giỏ hàng.
    public void addProduct(Product product){
        if (!checkItemInCart(product)){
            products.put(product,1);
        } else {
            Map.Entry<Product, Integer> itemEntry = selectItemInCart(product);
            Integer newQuantity = itemEntry.getValue() + 1;
            products.replace(itemEntry.getKey(),newQuantity);
        }
    }

    //countProductQuantity() dùng để đếm số lượng sản phẩm đó hiện có trong giỏ hàng.
    public Integer countProductQuantity(){
        Integer productQuantity = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }

    //countItemQuantity() để đếm số lượng sản phẩm có trong giỏ hàng.
    public Integer countItemQuantity(){
        return products.size();
    }

    //countTotalPayment() dùng để tính tổng số tiền cần phải thanh toán.
    public Double countTotalPayment(){
        Double payment = 0.0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            payment += entry.getKey().getPrice() * (float) entry.getValue();
        }
        return payment;
    }
}
