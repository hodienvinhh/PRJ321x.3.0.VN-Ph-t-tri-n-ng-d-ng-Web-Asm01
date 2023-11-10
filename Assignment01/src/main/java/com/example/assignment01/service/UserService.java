package com.example.assignment01.service;

import com.example.assignment01.entity.Role;
import com.example.assignment01.entity.User;
import com.example.assignment01.form.CreateUserForm;
import com.example.assignment01.form.UpdateUserForm;
import com.example.assignment01.repository.IRoleRepository;
import com.example.assignment01.repository.IUserRepository;
import com.example.assignment01.specification.user.UserSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAllUser(Pageable pageable, String search) {
        Specification<User> where = UserSpecification.buildWhere(search);
        Page<User> page = userRepository.findAll(where, pageable);
        return page;
    }

    @Override
    public User getUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new RuntimeException("Không tìm thấy User có là :" + id);
        }
        return user;
    }

    @Override
    public void lockOrUnlock(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            if (user.getStatus() == 1)
                user.setStatus(0);
            else
                user.setStatus(1);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Không tìm thấy User có là :" + id);
        }
    }

    @Override
    public void createUser(CreateUserForm form) {
        User user = new User();
        user = addNewUser(form);
        userRepository.save(user);
    }
    public User addNewUser(CreateUserForm form){
        User user = new User();
        user.setFullName(form.getFullName());
        user.setPhoneNumber(form.getPhoneNumber());
        user.setUserName(form.getUserName());
        user.setEmail(form.getEmail());
        user.setAddress(form.getAddress());
        user.setPassWord(bCryptPasswordEncoder.encode(form.getPassword()));
        Optional<Role> optionalRole = roleRepository.findById(form.getIdRole());
        if (!optionalRole.isPresent())
            throw new RuntimeException("Not found role");
        user.setRole(optionalRole.get());
        return user;
    }


    @Override
    public void updateUser(UpdateUserForm form) {
        Optional<User> optionalUser = userRepository.findById(form.getId());//user trc khi chinh sua
        User user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            user = asUser(user, form);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Không tìm thấy User có ID là :" + form.getId());
        }
    }

    public User asUser(User user, UpdateUserForm form) {
        user.setAddress(form.getAddress());
        user.setFullName(form.getFullName());
        user.setPhoneNumber(form.getPhoneNumber());
        Optional<Role> optionalRole = roleRepository.findById(form.getIdRole());
        if (!optionalRole.isPresent())
            throw new RuntimeException("Not found role");
        user.setRole(optionalRole.get());
        return user;
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        boolean status = false;
        status = user.getStatus() == 1 ? true: false;
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Username not found");
        } else {
            return new org.springframework.security.core.userdetails.User(username, user.getPassWord(), true, true, true, status, AuthorityUtils.createAuthorityList(user.getRole().getRoleName()));
        }
    }

}
