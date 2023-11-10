package com.example.assignment01.service;

import com.example.assignment01.entity.Donation;
import com.example.assignment01.form.CreateDonationForm;
import com.example.assignment01.form.UpdateDonationForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface IDonationService {
    List<Donation> getAllDonations();
    Page<Donation> getAllDonations(Pageable pageable, String search);

    public void createDonation(CreateDonationForm form) throws ParseException;

    public Donation  getDonationById(int id);

    public void updateDonation(UpdateDonationForm form) throws ParseException;

    public void deleteDonation(int id);
    public void changeStatus(int id);



}
