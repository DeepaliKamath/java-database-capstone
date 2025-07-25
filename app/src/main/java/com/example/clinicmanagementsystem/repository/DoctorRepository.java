package com.example.clinicmanagementsystem.repository;

import com.example.clinicmanagementsystem.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

        Doctor findByEmail(String email);

        @Query("SELECT d FROM Doctor d WHERE d.name LIKE CONCAT('%', :name, '%')")
        List<Doctor> findByNameLike(String name);

        @Query("SELECT d FROM Doctor d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%')) AND LOWER(d.specialty) = LOWER(:specialty)")
        List<Doctor> findByNameContainingIgnoreCaseAndSpecialtyIgnoreCase(String name, String specialty);

        List<Doctor> findBySpecialtyIgnoreCase(String specialty);
}