package com.example.demo.model;

import com.example.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByStatusIgnoreCase(String status); // For filtering by status
}
