package com.example.assignment01.controller;

import com.example.assignment01.entity.Donation;
import com.example.assignment01.entity.Role;
import com.example.assignment01.entity.User;
import com.example.assignment01.entity.UserDonation;
import com.example.assignment01.form.CreateDetailForm;
import com.example.assignment01.form.CreateUserForm;
import com.example.assignment01.form.UpdateUserForm;
import com.example.assignment01.service.IDonationService;
import com.example.assignment01.service.IRoleService;
import com.example.assignment01.service.IUserDonationService;
import com.example.assignment01.service.IUserService;
//import com.example.assignment01.utils.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    private IDonationService donationService;
    @Autowired
    private IUserDonationService userDonationService;
    @Autowired
    private IRoleService roleService;

    @GetMapping("/login")
    public String loginPage(){
        return "public/login";
    }


    @GetMapping("/home-donation")
    public String listDonation(ModelMap modelMap, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size,
                               @RequestParam(value = "keyword", required = false) String search) {
        int p = page.isPresent() ? page.get() : 0;
        int s = size.isPresent() ? size.get() : 5;
        Page<Donation> pages = donationService.getAllDonations(PageRequest.of(p, s), search);
        modelMap.addAttribute("listDonation", pages);
        return "public/home-donation";
    }
    @PostMapping(value = "/ql-donation/createDonationDetail")
    public String createUserDetail(@ModelAttribute CreateDetailForm form){
        userDonationService.createUserDetail(form);
        return "redirect:/home-donation";

    }

}
