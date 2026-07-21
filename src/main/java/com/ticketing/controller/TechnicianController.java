package com.ticketing.controller;

import com.ticketing.model.Technician;
import com.ticketing.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/technicians")
@CrossOrigin(origins = "http://localhost:3000")
public class TechnicianController {

    @Autowired
    private TechnicianRepository technicianRepository;

    @GetMapping
    public List<Technician> getAllTechnicians() {
        return technicianRepository.findAll();
    }

    @PostMapping
    public Technician createTechnician(@RequestBody Technician technician) {
        return technicianRepository.save(technician);
    }
}