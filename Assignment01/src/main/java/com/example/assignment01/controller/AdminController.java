package com.example.assignment01.controller;

import com.example.assignment01.entity.Role;
import com.example.assignment01.entity.User;
import com.example.assignment01.form.CreateUserForm;
import com.example.assignment01.form.UpdateUserForm;
import com.example.assignment01.service.IDonationService;
import com.example.assignment01.service.IRoleService;
import com.example.assignment01.service.IUserDonationService;
import com.example.assignment01.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class AdminController {
    @Autowired
    private IDonationService donationService;
    @Autowired
    private IUserDonationService userDonationService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;



    @GetMapping("/admin/login")
    public String loginPage(){
        return "admin/login";
    }

    @GetMapping("/admin/home")
    public String adminHome(){
        return "/admin/home";
    }

    @GetMapping("/admin/account")// /admin/account?page=&size
    public String getAll(ModelMap modelMap,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size,
                         @RequestParam(value = "keyword", required = false) String search
                        ) {
        int p = page.isPresent() ? page.get() : 0;
        int s = size.isPresent() ? size.get() : 5;
        Page<User> pages = userService.findAllUser(PageRequest.of(p, s), search);
        List<Role> roles = roleService.getAll();
        modelMap.addAttribute("list", pages);
        modelMap.addAttribute("roleList", roles);
        modelMap.addAttribute("pageNumber", pages.getNumber());
        return "/admin/account";
    }


    @PostMapping(value = "/admin/deleteUser")
    public String deleteUser(@RequestParam int id){
        userService.deleteUser(id);
        return "redirect:/admin/account";
    }

    // lock account function
    @PostMapping("/admin/lockUser")
    public String lockAccount(@RequestParam int id) {
        userService.lockOrUnlock(id);
        return "redirect:/admin/account";
    }

    // unlock account function
    @PostMapping("/admin/un-lockUser")
    public String unlockAccount(@RequestParam int id) {
        userService.lockOrUnlock(id);
        return "redirect:/admin/account";
    }

    @PostMapping(value = "/admin/updateUser")
    public String updateUser(@ModelAttribute UpdateUserForm form) {
        userService.updateUser(form);
        return "redirect:/admin/account";
    }

    @PostMapping(value = "/admin/createUser")
    public String createUser(@ModelAttribute CreateUserForm form) {
        userService.createUser(form);
        return "redirect:/admin/account";
    }
    @PostMapping(value = "/admin/send-mail")
    public String sendEmail(){
        return "redirect:/admin/account";
    }

}
