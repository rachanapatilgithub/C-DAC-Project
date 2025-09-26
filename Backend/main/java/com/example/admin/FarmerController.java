package com.example.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    @Autowired
    private FarmerRepository farmerRepository;

    // Register Farmer
    @PostMapping("/register")
    public ResponseEntity<String> registerFarmer(@RequestBody Farmer farmer) {
        if (farmerRepository.existsByEmail(farmer.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered.");
        }
        if (farmerRepository.existsByAadhaar(farmer.getAadhaar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Aadhaar already registered.");
        }

        farmerRepository.save(farmer);
        return ResponseEntity.ok("Farmer registered successfully.");
    }

    // Login Farmer
    @PostMapping("/login")
    public ResponseEntity<?> loginFarmer(@RequestBody Farmer farmer) {
        Optional<Farmer> existingFarmer = farmerRepository.findByUsernameAndPassword(
                farmer.getUsername(), farmer.getPassword());

        if (existingFarmer.isPresent()) {
            return ResponseEntity.ok(existingFarmer.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }

    // Get total number of farmers
    @GetMapping("/count")
    public ResponseEntity<Long> getFarmerCount() {
        return ResponseEntity.ok(farmerRepository.count());
    }

    // Get all farmers
    @GetMapping
    public ResponseEntity<List<Farmer>> getAllFarmers() {
        return ResponseEntity.ok(farmerRepository.findAll());
    }

    // Delete farmer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFarmer(@PathVariable("id") Long id) {
        return farmerRepository.findById(id)
                .map(farmer -> {
                    farmerRepository.delete(farmer);
                    return ResponseEntity.ok("Farmer deleted successfully.");
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farmer not found."));
    }

    // Update farmer by ID
    @PutMapping("/{id}")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable Long id, @RequestBody Farmer farmer) {
        Optional<Farmer> updated = updateFarmerData(id, farmer);
        return updated
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Helper method to update farmer data
    private Optional<Farmer> updateFarmerData(Long id, Farmer updatedFarmer) {
        return farmerRepository.findById(id).map(existing -> {
            if (updatedFarmer.getUsername() != null) existing.setUsername(updatedFarmer.getUsername());
            if (updatedFarmer.getGender() != null) existing.setGender(updatedFarmer.getGender());
            if (updatedFarmer.getDob() != null) existing.setDob(updatedFarmer.getDob());
            if (updatedFarmer.getMobile() != null) existing.setMobile(updatedFarmer.getMobile());
            if (updatedFarmer.getEmail() != null) existing.setEmail(updatedFarmer.getEmail());
            if (updatedFarmer.getAadhaar() != null) existing.setAadhaar(updatedFarmer.getAadhaar());
            if (updatedFarmer.getState() != null) existing.setState(updatedFarmer.getState());
            if (updatedFarmer.getDistrict() != null) existing.setDistrict(updatedFarmer.getDistrict());
            if (updatedFarmer.getCity() != null) existing.setCity(updatedFarmer.getCity());
            if (updatedFarmer.getPinCode() != null) existing.setPinCode(updatedFarmer.getPinCode());
            if (updatedFarmer.getLandArea() != null) existing.setLandArea(updatedFarmer.getLandArea());
            if (updatedFarmer.getLandType() != null) existing.setLandType(updatedFarmer.getLandType());
            if (updatedFarmer.getSoilType() != null) existing.setSoilType(updatedFarmer.getSoilType());
            if (updatedFarmer.getFarmingCategory() != null) existing.setFarmingCategory(updatedFarmer.getFarmingCategory());

            if (updatedFarmer.getCrops() != null) existing.setCrops(updatedFarmer.getCrops());
            if (updatedFarmer.getLivestock() != null) existing.setLivestock(updatedFarmer.getLivestock());

            if (updatedFarmer.getPassword() != null) existing.setPassword(updatedFarmer.getPassword());

            Farmer saved = farmerRepository.save(existing);
            return saved;
        });
    }
}
