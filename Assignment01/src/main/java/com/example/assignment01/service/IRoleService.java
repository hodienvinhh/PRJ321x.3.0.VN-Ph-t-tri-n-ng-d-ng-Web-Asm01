package com.example.assignment01.service;

import com.example.assignment01.entity.Role;

import java.util.List;

public interface IRoleService {
    public Role getRoleById(int id);
    public List<Role> getAll();

}
