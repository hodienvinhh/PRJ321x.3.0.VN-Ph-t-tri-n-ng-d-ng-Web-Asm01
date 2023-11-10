package com.example.assignment01.service;

import com.example.assignment01.entity.Donation;
import com.example.assignment01.entity.Role;
import com.example.assignment01.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Override
    public Role getRoleById(int id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        Role role = null;
        if (optionalRole.isPresent()){
            role= optionalRole.get();
        }else {
            throw new RuntimeException("Không tìm thấy Role có " + id);
        }
        return role;

    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }


}
