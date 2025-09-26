package com.example.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    // ✅ Find admin by username and password for login
    Optional<Admin> findByUsernameAndPassword(String username, String password);

    // ✅ Check if a username already exists to prevent duplicates during registration
    Optional<Admin> findByUsername(String username);
}
