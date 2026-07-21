package com.ticketing.repository;

import com.ticketing.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {
    Optional<Technician> findByNameAndPassword(String name, String password);
}