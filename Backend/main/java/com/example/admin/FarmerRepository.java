package com.example.admin;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    // Check if email already exists (for registration)
    boolean existsByEmail(String email);

    // Check if Aadhaar already exists (for registration)
    boolean existsByAadhaar(String aadhaar);

    // Used for login
    Optional<Farmer> findByUsernameAndPassword(String username, String password);

    // ✅ NEW (Optional): Find all farmers (already provided by JpaRepository)
    List<Farmer> findAll();
}
