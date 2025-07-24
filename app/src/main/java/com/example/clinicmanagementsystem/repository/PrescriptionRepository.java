package com.example.clinicmanagementsystem.repository;

import com.example.clinicmanagementsystem.models.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PrescriptionRepository extends MongoRepository<Prescription, String> {
        List<Prescription> findByAppointmentId(Long appointmentId);
}