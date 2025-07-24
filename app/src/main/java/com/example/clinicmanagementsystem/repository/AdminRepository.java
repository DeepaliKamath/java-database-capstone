package com.example.clinicmanagementsystem.repository;

import com.example.clinicmanagementsystem.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
        Admin findByUsername(String username);
}
