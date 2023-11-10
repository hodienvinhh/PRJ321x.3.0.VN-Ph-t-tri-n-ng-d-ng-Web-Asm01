package com.example.assignment01.controller;

import com.example.assignment01.entity.Donation;
import com.example.assignment01.form.CreateDonationForm;
import com.example.assignment01.form.UpdateDonationForm;
import com.example.assignment01.service.IDonationService;
import com.example.assignment01.service.IRoleService;
import com.example.assignment01.service.IUserDonationService;
import com.example.assignment01.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Optional;

@Controller
public class DonationController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IDonationService donationService;
    @Autowired
    private IUserDonationService userDonationService;


    @GetMapping("/admin/donation")
    public String listDonation(ModelMap modelMap, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size,
                               @RequestParam(value = "keyword", required = false) String search) {
        int p = page.isPresent() ? page.get() : 0;
        int s = size.isPresent() ? size.get() : 5;
        Page<Donation> pages = donationService.getAllDonations(PageRequest.of(p, s),search);
        modelMap.addAttribute("listUserDonation", pages);
        modelMap.addAttribute("keyword", search);
        return "/admin/donation";
    }

    @PostMapping(value = "/admin/deleteDonation")
    public String deleteUser(@RequestParam int id) {
        donationService.deleteDonation(id);
        return "redirect:/admin/donation";
    }

    @PostMapping(value = "/admin/updateDonation")
    public String updateDonation(@ModelAttribute UpdateDonationForm form) throws ParseException {
        donationService.updateDonation(form);
        return "redirect:/admin/donation";
    }

    @PostMapping(value = "/admin/createNewDonation")
    public String createDonation(@ModelAttribute CreateDonationForm form) throws ParseException {
        donationService.createDonation(form);
        return "redirect:/admin/donation";
    }

    @PostMapping(value = "/admin/startDonation")
    public String createDonation(@RequestParam int id){
        donationService.changeStatus(id);
        return "redirect:/admin/donation";
    }
    @PostMapping(value = "/admin/endDonation")
    public String endDonation(@RequestParam int id){
        donationService.changeStatus(id);
        return "redirect:/admin/donation";
    }
    @PostMapping(value = "/admin/closeDonation")
    public String closeDonation(@RequestParam int id){
        donationService.changeStatus(id);
        return "redirect:/admin/donation";
    }

}

