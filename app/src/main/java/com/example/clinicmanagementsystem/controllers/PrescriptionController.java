package com.example.clinicmanagementsystem.controllers;

import com.example.clinicmanagementsystem.service.AppointmentService;
import com.example.clinicmanagementsystem.service.PrescriptionService;
import com.example.clinicmanagementsystem.service.Servicess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashMap;
import java.util.Map;

import jakarta.validation.Valid;
import com.example.clinicmanagementsystem.models.Prescription;

@RestController
@RequestMapping("${api.path}" + "prescription")
public class PrescriptionController {

        private final PrescriptionService prescriptionService;
        private final Servicess service;
        private final AppointmentService appointmentService;

        @Autowired
        public PrescriptionController(PrescriptionService prescriptionService, Servicess service,AppointmentService appointmentService) {
            this.prescriptionService = prescriptionService;
            this.service = service;
            this.appointmentService=appointmentService;
        }

        @PostMapping("/{token}")
        public ResponseEntity<Map<String, String>> savePrescription(@PathVariable String token, @RequestBody @Valid Prescription prescription) {
            ResponseEntity<Map<String, String>> tempMap = service.validateToken(token, "doctor");
            if (!tempMap.getBody().isEmpty()) {
                return tempMap;
            }
            appointmentService.changeStatus(prescription.getAppointmentId());
            return prescriptionService.savePrescription(prescription);
        }

        @GetMapping("/{appointmentId}/{token}")
        public ResponseEntity<Map<String, Object>> getPrescription(@PathVariable Long appointmentId,@PathVariable String token)
        {
            Map<String, Object> map = new HashMap<>();
            ResponseEntity<Map<String,String>> tempMap= service.validateToken(token, "doctor");
            if (!tempMap.getBody().isEmpty()) {
                map.putAll(tempMap.getBody());
                return new ResponseEntity<>(map, tempMap.getStatusCode());
            }
            return prescriptionService.getPrescription(appointmentId);
        }
    }