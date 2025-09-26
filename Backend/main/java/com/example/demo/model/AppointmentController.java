package com.example.demo.model;

import com.example.demo.model.Appointment;
import com.example.demo.model.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:3000") // adjust if needed
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Get all appointments
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Get appointment by ID
    @GetMapping("/{id}")
    public Optional<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentRepository.findById(id);
    }

    // Create new appointment
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Update appointment
    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppointment) {
        return appointmentRepository.findById(id).map(appointment -> {
            appointment.setFarmerName(updatedAppointment.getFarmerName());
            appointment.setExpertName(updatedAppointment.getExpertName());
            appointment.setIssue(updatedAppointment.getIssue());
            appointment.setDateTime(updatedAppointment.getDateTime());
            appointment.setStatus(updatedAppointment.getStatus());
            return appointmentRepository.save(appointment);
        }).orElseGet(() -> {
            updatedAppointment.setId(id);
            return appointmentRepository.save(updatedAppointment);
        });
    }

    // Delete appointment
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
    }

    // Filter by status (optional)
    @GetMapping("/status/{status}")
    public List<Appointment> getAppointmentsByStatus(@PathVariable String status) {
        return appointmentRepository.findByStatusIgnoreCase(status);
    }
}
