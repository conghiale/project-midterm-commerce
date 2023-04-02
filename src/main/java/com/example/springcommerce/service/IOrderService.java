package com.example.springcommerce.service;

import com.example.springcommerce.model.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.UUID;

public interface IOrderService {
    public List<Order> selectAll();
    public Order selectById(int id);
    public Order insert(Order order);
    public boolean deleteById(int id);
    public Order update(int id, Order orderUpdate);
    public List<Order> findOrdersByCustomerIdLike(UUID customerId);
}
