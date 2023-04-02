package com.example.springcommerce.service;

import com.example.springcommerce.model.Customer;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {
    public List<Customer> selectAll();
    public Customer selectById(UUID id);
    public Customer selectByUsername(String username);
    public Customer insert(Customer customer);
    public boolean deleteById(UUID id);

    public Customer update(UUID id, Customer customerUpdate);
}
