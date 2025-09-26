package com.example.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    // Validate admin input
    private boolean isInvalidAdmin(Admin admin) {
        return admin.getUsername() == null || admin.getUsername().trim().isEmpty()
            || admin.getPassword() == null || admin.getPassword().trim().isEmpty();
    }

    // Check if username already exists
    private boolean isUsernameTaken(String username) {
        return adminRepository.findByUsername(username).isPresent();
    }

    // Register admin
    @PostMapping("/register")
    public ResponseEntity<Object> registerAdmin(@RequestBody Admin admin) {
        if (isInvalidAdmin(admin)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Username and password are required.");
        }

        if (isUsernameTaken(admin.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Username already exists. Please choose another.");
        }

        Admin savedAdmin = adminRepository.save(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
    }

    // Login admin
    @PostMapping("/login")
    public ResponseEntity<Object> loginAdmin(@RequestBody Admin loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Username and password are required.");
        }

        Optional<Admin> optionalAdmin = adminRepository.findByUsernameAndPassword(
            loginRequest.getUsername().trim(),
            loginRequest.getPassword().trim()
        );

        if (optionalAdmin.isPresent()) {
            return ResponseEntity.ok(optionalAdmin.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username or password.");
        }
    }

    // Get all admins
    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok(adminRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAdminById(@PathVariable Long id) {
        return adminRepository.findById(id)
            .<ResponseEntity<Object>>map(admin -> ResponseEntity.ok().body(admin))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Admin not found."));
    }

    // Delete admin by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAdmin(@PathVariable Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return ResponseEntity.ok("Admin deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found.");
        }
    }
}
