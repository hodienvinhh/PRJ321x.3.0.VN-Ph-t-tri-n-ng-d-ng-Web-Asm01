package com.example.assignment01.service;

import com.example.assignment01.entity.UserDonation;
import com.example.assignment01.form.CreateDetailForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserDonationService {
    List<UserDonation> findAll();
    Page<UserDonation> findAllUserDonation(Pageable pageable);
    public UserDonation getUserDonationById(int id);

    public void createUserDonation(UserDonation userDonation);

    public void deleteUserDonationById(int id);

    List<UserDonation> findByDonationId(int donationId);

    void createUserDetail(CreateDetailForm form);//form moi tao

    void updateUserDonationDetail(int status, int id);
}
