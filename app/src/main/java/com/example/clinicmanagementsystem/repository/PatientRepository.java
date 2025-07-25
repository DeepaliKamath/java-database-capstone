package com.example.clinicmanagementsystem.repository;

import com.example.clinicmanagementsystem.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
        Patient findByEmail(String email);
        Patient findByEmailOrPhone(String email, String phone);
}