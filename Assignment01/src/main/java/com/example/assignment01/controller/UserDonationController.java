package com.example.assignment01.controller;

import com.example.assignment01.entity.Donation;
import com.example.assignment01.entity.UserDonation;
import com.example.assignment01.service.IDonationService;
import com.example.assignment01.service.IUserDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserDonationController {
    @Autowired
    private IUserDonationService userDonationService;

    @Autowired
    private IDonationService donationService;


    @GetMapping("/admin/detailDonation")
    public String getUserDonationsByDonationId(@RequestParam int donationId, ModelMap modelMap) {
        List<UserDonation> userDonations = userDonationService.findByDonationId(donationId);
        modelMap.addAttribute("userDonationList", userDonations);// ds nhung chi tiet donate
        Donation donation = donationService.getDonationById(donationId);
        modelMap.addAttribute("donation", donation);
        return "/admin/detail";
    }
    @GetMapping("/detail")
    public String getDetailUserDonationsByDonationId(@RequestParam int donationId, ModelMap modelMap) {
        List<UserDonation> userDonations = userDonationService.findByDonationId(donationId);
        modelMap.addAttribute("userDonationList", userDonations);// ds nhung chi tiet donate
        Donation donation = donationService.getDonationById(donationId);
        modelMap.addAttribute("donation", donation);
        return "public/detail";
    }


    @PostMapping("/admin/update-donate")
    public String updateUserDonationDetail(@RequestParam int status, @RequestParam int id, @RequestParam int donationId){
        userDonationService.updateUserDonationDetail(status, id);
        return "redirect:/admin/detailDonation?donationId="+donationId;
    }
}
