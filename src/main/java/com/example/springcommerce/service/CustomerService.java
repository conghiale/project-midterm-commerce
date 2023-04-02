package com.example.springcommerce.service;

import com.example.springcommerce.model.Customer;
import com.example.springcommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> selectAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer selectById(UUID id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.orElse(null);
    }

    @Override
    public Customer selectByUsername(String username) {
        Customer customer = customerRepository.findCustomerByUsernameLike(username);
        return customer;
    }

    @Override
    public Customer insert(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean deleteById(UUID id) {
        if (customerRepository.existsById(id))
            customerRepository.deleteById(id);
        return !customerRepository.existsById(id);
    }

    @Override
    public Customer update(UUID id, Customer customerUpdate) {
        Customer customer = selectById(id);
        customer.setName(customerUpdate.getName());
        customer.setUsername(customerUpdate.getUsername());
        customer.setEmail(customerUpdate.getEmail());
        customer.setPassword(customerUpdate.getPassword());
        customer.setDob(customerUpdate.getDob());
        customer.setGender(customerUpdate.getGender());
        return customerRepository.save(customer);
    }
}
