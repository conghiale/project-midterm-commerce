package com.example.springcommerce.repository;

import com.example.springcommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    public List<Order> findOrdersByCustomerIdLike(UUID customerId);
}
