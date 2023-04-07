package com.example.springcommerce.service;

import com.example.springcommerce.model.Role;
import com.example.springcommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findById(Integer id) {
        Role role = roleRepository.findById(id).get();
        if(role != null) {
            return role;
        }
        return null;
    }
}
