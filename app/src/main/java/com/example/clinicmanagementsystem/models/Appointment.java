package com.example.clinicmanagementsystem.models;

import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.validation.constraints.*;

@Entity
@Table(name =  "Appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "Doctor must be assigned to the appointment")
    private Doctor doctor;

    @ManyToOne
    @NotNull(message = "Patient must be assigned to the appointment")
    private Patient patient;

    @Future(message = "Appointment time must be in the future")
    private LocalDateTime appointmentTime;

    private int status; // 0 = Scheduled, 1 = Completed

    //constructors
    public Appointment() {

    }

    public Appointment(Long id, Doctor doctor, Patient patient, LocalDateTime appointmentTime, int status) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }
//getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // getEndTime() method
    @Transient
    public LocalDateTime getEndTime() {
        return appointmentTime != null ? appointmentTime.plusHours(1) : null;
    }

    // getAppointmentDate() method
    public LocalDate getAppointmentDate() {
        return appointmentTime != null ? appointmentTime.toLocalDate() : null;
    }

    // getAppointmentTimeOnly() method
    public LocalTime getAppointmentTimeOnly() {
        return appointmentTime != null ? appointmentTime.toLocalTime() : null;  // Extracts only the time
    }
}
