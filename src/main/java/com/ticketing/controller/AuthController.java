package com.ticketing.controller;

import com.ticketing.model.Admin;
import com.ticketing.model.Employee;
import com.ticketing.model.Technician;
import com.ticketing.repository.AdminRepository;
import com.ticketing.repository.EmployeeRepository;
import com.ticketing.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String name = credentials.get("name");
        String password = credentials.get("password");

        // Check Admin
        Optional<Admin> admin = adminRepository.findByNameAndPassword(name, password);
        if (admin.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", admin.get().getId());
            response.put("name", admin.get().getName());
            response.put("role", "ADMIN");
            return ResponseEntity.ok(response);
        }

        // Check Employee
        Optional<Employee> employee = employeeRepository.findByNameAndPassword(name, password);
        if (employee.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", employee.get().getId());
            response.put("name", employee.get().getName());
            response.put("role", "EMPLOYEE");
            return ResponseEntity.ok(response);
        }

        // Check Technician
        Optional<Technician> technician = technicianRepository.findByNameAndPassword(name, password);
        if (technician.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", technician.get().getId());
            response.put("name", technician.get().getName());
            response.put("role", "TECHNICIAN");
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }
}