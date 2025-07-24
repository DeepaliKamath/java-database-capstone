package com.example.clinicmanagementsystem.mvc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.clinicmanagementsystem.service.Servicess;

@Controller
public class DashboardController {

        @Autowired
        Servicess service;

        @GetMapping("/adminDashboard/{token}")
        public String adminDashboard(@PathVariable String token)
        {
            Map<String, String> map=service.validateToken(token,"admin").getBody();
            System.out.println("map"+map);
            if(map.isEmpty())
            {
                return "admin/adminDashboard";
            }
            return "redirect:http://localhost:8080";

        }

        @GetMapping("/doctorDashboard/{token}")
        public String doctorDashboard(@PathVariable String token)
        {
            Map<String, String> map=service.validateToken(token,"doctor").getBody();
            System.out.println("map"+map);
            if(map.isEmpty())
            {
                return "doctor/doctorDashboard";
            }

            return "redirect:http://localhost:8080";

        }
}
