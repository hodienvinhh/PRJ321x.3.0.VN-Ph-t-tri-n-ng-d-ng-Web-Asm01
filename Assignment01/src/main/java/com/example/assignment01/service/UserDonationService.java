package com.example.assignment01.service;

import com.example.assignment01.entity.Donation;
import com.example.assignment01.entity.User;
import com.example.assignment01.entity.UserDonation;
import com.example.assignment01.form.CreateDetailForm;
import com.example.assignment01.repository.IDonationRepository;
import com.example.assignment01.repository.IUserDonationRepository;
import com.example.assignment01.repository.IUserRepository;
import com.example.assignment01.utils.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserDonationService implements IUserDonationService {
    @Autowired
    private IUserDonationRepository userDonationRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IDonationRepository donationRepository;

    @Override
    public List<UserDonation> findAll() {
      return  userDonationRepository.findAll();
    }

    @Override
    public Page<UserDonation> findAllUserDonation(Pageable pageable) {
        Page<UserDonation> page = userDonationRepository.findAll(pageable);
        return page;
    }

    @Override
    public UserDonation getUserDonationById(int id) {
        Optional<UserDonation> optionalUserDonation = userDonationRepository.findById(id);
        UserDonation userDonation = null;
        if (optionalUserDonation.isPresent()){
            userDonation = optionalUserDonation.get();
        }else {
            throw  new RuntimeException("Không tìm thấy Donation có " + id);
        }
        return userDonation;
    }

    @Override
    public void createUserDonation(UserDonation userDonation) {
        userDonationRepository.save(userDonation);

    }

    @Override
    public void deleteUserDonationById(int id) {
        userDonationRepository.deleteById(id);

    }

    @Override
    public List<UserDonation> findByDonationId(int donationId) {
       return userDonationRepository.findByDonationId(donationId);
    }

    @Override
    public void createUserDetail(CreateDetailForm form) {
        UserDonation userDonation = new UserDonation();
        userDonation.setName(form.getName());
        userDonation.setMoney(form.getMoney());
        userDonation.setText(form.getText());
        // tìm donation theo donation_id
        Donation donation = donationRepository.findById(form.getIdDonation()).get();
        userDonation.setDonation(donation);
        userDonation.setCreated(String.valueOf(LocalDate.now()));

        String email = ContextUtils.getEmail();
        User user = userRepository.findByEmail(email);
        userDonation.setUser(user);
        userDonationRepository.save(userDonation);
    }

    @Override
    public void updateUserDonationDetail(int status, int id) {
        //update cho userDonationDetail theo status o tren
        Optional<UserDonation> optionalUserDonation = userDonationRepository.findById(id);
        UserDonation userDonation = null;
        if (optionalUserDonation.isPresent()){
            userDonation = optionalUserDonation.get();
        }else {
            throw  new RuntimeException("Không tìm thấy Donation có " + id);
        }
        userDonation.setStatus(status);
        userDonationRepository.save(userDonation);
        //+ so tien vao donation theo idDonation lay tu userDonationDetail neweu status =1
        if (status == 1){
            //lay thong itn ve donation
            Donation donation = userDonation.getDonation();
            donation.setMoney(donation.getMoney() + userDonation.getMoney());
            donationRepository.save(donation);
        }
    }


}
