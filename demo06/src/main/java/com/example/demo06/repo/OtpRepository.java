package com.example.demo06.repo;

import com.example.demo06.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp,Integer> {

    Optional<Otp> findOtpByUsername(String username);
}
