package com.example.assignment01.repository;

import com.example.assignment01.entity.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDonationRepository extends JpaRepository<UserDonation,Integer> {

    List<UserDonation> findByDonationId(int donationId);
}
