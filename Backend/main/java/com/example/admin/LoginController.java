package com.example.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") // Allow frontend access
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private ExpertRepository expertRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Expert expert = expertRepository.findByUsername(username);

        if (expert != null && expert.getPassword().equals(password)) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
