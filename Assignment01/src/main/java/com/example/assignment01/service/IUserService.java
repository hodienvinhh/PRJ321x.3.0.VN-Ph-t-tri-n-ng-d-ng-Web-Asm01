package com.example.assignment01.service;

import com.example.assignment01.entity.Donation;
import com.example.assignment01.entity.User;
import com.example.assignment01.form.CreateUserForm;
import com.example.assignment01.form.UpdateUserForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

 public  List<User> findAllUser();
 public Page<User> findAllUser(Pageable pageable,String search);
 public User getUserById(int id);
 public void lockOrUnlock(int id);
 public void createUser(CreateUserForm form);
 public void updateUser(UpdateUserForm form);
 public void deleteUser(int id);



}
