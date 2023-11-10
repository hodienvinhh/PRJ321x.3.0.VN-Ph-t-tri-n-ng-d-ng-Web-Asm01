package com.example.assignment01.repository;

import com.example.assignment01.entity.Donation;
import com.example.assignment01.entity.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDonationRepository extends JpaRepository<Donation, Integer> , JpaSpecificationExecutor<Donation> {
}
