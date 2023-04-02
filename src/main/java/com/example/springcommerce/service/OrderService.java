package com.example.springcommerce.service;

import com.example.springcommerce.model.Order;
import com.example.springcommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findOrdersByCustomerIdLike(UUID customerId) {
        return orderRepository.findOrdersByCustomerIdLike(customerId);
    }

    @Override
    public List selectAll() {
        return null;
    }

    @Override
    public Order selectById(int id) {
        Optional<Order> customerOptional = orderRepository.findById(id);
        return customerOptional.orElse(null);
    }
    @Override
    public Order insert(Order cart) {
        return orderRepository.save(cart);
    }

    @Override
    public boolean deleteById(int id) {
        if (orderRepository.existsById(id))
            orderRepository.deleteById(id);
        return !orderRepository.existsById(id);
    }

    @Override
    public Order update(int id, Order objectUpdate) {
        return null;
    }

//    @Override
//    public Cart update(int id, Cart cartUpdate) {
//        Cart cart = selectById(id);
//        cart.setQuantity(cartUpdate.getQuantity());
//        cart.setCost(cartUpdate.getCost());
//        cart.setProductId(cartUpdate.getProductId());
//        return cartRepository.save(cart);
//    }
}
