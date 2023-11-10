package com.example.assignment01.service;

import com.example.assignment01.entity.Donation;
import com.example.assignment01.entity.User;
import com.example.assignment01.form.CreateDonationForm;
import com.example.assignment01.form.UpdateDonationForm;
import com.example.assignment01.repository.IDonationRepository;
import com.example.assignment01.repository.IUserRepository;
import com.example.assignment01.specification.donation.DonationSpecification;
import com.example.assignment01.specification.user.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DonationService implements IDonationService{
    @Autowired
   private IDonationRepository donationRepository;
    @Autowired
   private IUserRepository userRepository;

    @Override
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    @Override
    public Page<Donation> getAllDonations(Pageable pageable, String search) {
        //chuyen string to value
        String status1 = "mới tạo";
        String status2 = "đang quyên góp";
        String status3 = "kết thúc quyên góp";
        String status4 = "đóng quyên góp";
        String status  ="";
        Specification<Donation> where = null;
        if (Objects.nonNull(search)){
            if (status1.contains(search.toLowerCase()))
                status += "0";
            if (status2.contains(search.toLowerCase()))
                status += "1";
            if (status3.contains(search.toLowerCase()))
                status += "2";
            if (status4.contains(search.toLowerCase()))
                status += "3";
        }





        if (!StringUtils.isEmpty(status))
            where = DonationSpecification.buildWhere(String.valueOf(status));
        Page<Donation> donationPages = donationRepository.findAll(where, pageable);
        return donationPages;
    }


    @Override
    public Donation getDonationById(int id) {
        Optional<Donation> optionalDonation = donationRepository.findById(id);
        Donation donation = null;
        if (optionalDonation.isPresent()){
            donation= optionalDonation.get();
        }else {
            throw new RuntimeException("Không tìm thấy Donation có " + id);
        }
        return donation;

    }

    @Override
    public void updateDonation(UpdateDonationForm form) throws ParseException {
        Optional<Donation> optional = donationRepository.findById(form.getId());
        Donation donation = null;
            if (optional.isPresent()) {
                donation = optional.get();
                donation = asDonation(donation, form);
                donationRepository.save(donation);
            } else {
                throw new RuntimeException("Không tìm thấy Donation có ID là :" + form.getId());
            }
        }


    public Donation asDonation(Donation donation, UpdateDonationForm form) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        donation.setCode(form.getCode());
        donation.setName(form.getName());
        donation.setStartDate(dateFormat.parse(form.getStartDate()));
        donation.setEndDate(dateFormat.parse(form.getEndDate()));
        donation.setOrganizationName(form.getOrganizationName());
        donation.setPhoneNumber(form.getPhoneNumber());
        donation.setDescription(form.getDescription());
        return donation;
    }
    @Override
    public void deleteDonation(int id) {
        donationRepository.deleteById(id);

    }

    @Override
    public void changeStatus(int id) {
        Optional<Donation> optionalDonation = donationRepository.findById(id);
        Donation donation = null;
        if (optionalDonation.isPresent()){
            donation = optionalDonation.get();
            // Nếu status hiện tại là 0 thì set giá trị là 1
            if (donation.getStatus() == 0) {
                donation.setStatus(1);
            }
            // Nếu status hiện tại là 1 thì set giá trị là 2
            else if (donation.getStatus() == 1) {
                donation.setStatus(2);
            }
            // Nếu status hiện tại là 2 thì set giá trị là 3
            else if (donation.getStatus() == 2) {
                donation.setStatus(3);
            }

            donationRepository.save(donation);
        }else {
            throw new RuntimeException("Không tìm thấy Donation có là :" + id);
        }
    }

    public void createDonation(CreateDonationForm form) throws ParseException {
        Donation donation = new Donation();
        donation = addNewDonation(form);
        donationRepository.save(donation);
    }
    public Donation addNewDonation(CreateDonationForm form) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Donation donation = new Donation();
        donation.setCode(form.getCode());
        donation.setName(form.getName());
        donation.setStartDate(dateFormat.parse(form.getStartDate()));
        donation.setEndDate(dateFormat.parse(form.getEndDate()));
        donation.setOrganizationName(form.getOrganizationName());
        donation.setPhoneNumber(form.getPhoneNumber());
        donation.setDescription(form.getDescription());
        donation.setCreated(String.valueOf(LocalDate.now()));
        return donation;
    }



}