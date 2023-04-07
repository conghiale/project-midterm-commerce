package com.example.springcommerce.service;

import com.example.springcommerce.model.Customer;
import com.example.springcommerce.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);

        if (customer == null) {
            throw new UsernameNotFoundException("User " + username + "was not found in database!");
        }

        List<String> roles = customerRepository.findRolesByUsername(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String role: roles) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantedAuthorities.add(authority);
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                customer.getUsername(),
                customer.getPassword(),
                grantedAuthorities);

        session.setAttribute("customerId", customer.getId().toString());
        session.setAttribute("username", customer.getUsername());

        return userDetails;
    }
}
