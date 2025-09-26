package com.example.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/experts")
public class ExpertController {

    @Autowired
    private ExpertRepository expertRepository;

    // ✅ GET all experts
    @GetMapping
    public ResponseEntity<List<Expert>> getAllExperts() {
        List<Expert> experts = expertRepository.findAll();
        return ResponseEntity.ok(experts);
    }

    // ✅ GET experts with filters (NEW)
    @GetMapping("/filter")
    public ResponseEntity<List<Expert>> filterExperts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String expertise,
            @RequestParam(required = false) String location) {

        List<Expert> experts = expertRepository.findByFilters(
                (name != null) ? name.trim() : "",
                (expertise != null) ? expertise.trim() : "",
                (location != null) ? location.trim() : ""
        );

        return ResponseEntity.ok(experts);
    }

    // ✅ GET expert by ID
    @GetMapping("/{id}")
    public ResponseEntity<Expert> getExpertById(@PathVariable Long id) {
        return expertRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // ✅ GET: Count total experts
    @GetMapping("/count")
    public ResponseEntity<Long> countExperts() {
        long count = expertRepository.count();
        return ResponseEntity.ok(count);
    }

    // ✅ GET: Check username availability
    @GetMapping("/check-username/{username}")
    public ResponseEntity<Boolean> isUsernameTaken(@PathVariable String username) {
        if (!StringUtils.hasText(username)) {
            return ResponseEntity.badRequest().body(false);
        }
        Expert existing = expertRepository.findByUsername(username.trim());
        return ResponseEntity.ok(existing != null);
    }

    // ✅ POST: Register expert
    @PostMapping("/register")
    public ResponseEntity<Object> registerExpert(@RequestBody Expert expert) {
        if (expert == null || !StringUtils.hasText(expert.getUsername()) || !StringUtils.hasText(expert.getPassword())) {
            return ResponseEntity.badRequest().body("Username and password are required.");
        }

        if (expertRepository.findByUsername(expert.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists.");
        }

        try {
            Expert savedExpert = expertRepository.save(expert);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedExpert);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register expert.");
        }
    }

    // ✅ POST: Login expert
    @PostMapping("/login")
    public ResponseEntity<Object> loginExpert(@RequestBody Expert loginRequest) {
        if (!StringUtils.hasText(loginRequest.getUsername()) || !StringUtils.hasText(loginRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Username and password are required.");
        }

        Expert foundExpert = expertRepository.findByUsernameAndPassword(
                loginRequest.getUsername().trim(), loginRequest.getPassword().trim()
        );

        if (foundExpert != null) {
            return ResponseEntity.ok(foundExpert);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }

    // ✅ PUT: Update expert
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpert(@PathVariable Long id, @RequestBody Expert updatedExpert) {
        Optional<Expert> optionalExpert = expertRepository.findById(id);

        if (optionalExpert.isPresent()) {
            Expert expert = optionalExpert.get();

            // Personal Info
            expert.setFullName(updatedExpert.getFullName());
            expert.setGender(updatedExpert.getGender());
            expert.setDob(updatedExpert.getDob());
            expert.setMobile(updatedExpert.getMobile());
            expert.setAlternatePhone(updatedExpert.getAlternatePhone());
            expert.setEmail(updatedExpert.getEmail());
            expert.setAadhaar(updatedExpert.getAadhaar());

            // Professional
            expert.setQualification(updatedExpert.getQualification());
            expert.setExpertise(updatedExpert.getExpertise());
            expert.setExperience(updatedExpert.getExperience());
            expert.setLanguages(updatedExpert.getLanguages());
            expert.setTimeSlots(updatedExpert.getTimeSlots());
            expert.setConsultationModes(updatedExpert.getConsultationModes());

            // Location
            expert.setState(updatedExpert.getState());
            expert.setDistrict(updatedExpert.getDistrict());
            expert.setCity(updatedExpert.getCity());
            expert.setPinCode(updatedExpert.getPinCode());

            // Account
            expert.setUsername(updatedExpert.getUsername());
            expert.setPassword(updatedExpert.getPassword());

            Expert savedExpert = expertRepository.save(expert);
            return ResponseEntity.ok(savedExpert);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Expert not found with ID: " + id);
        }
    }

    // ✅ DELETE: Delete expert
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpert(@PathVariable("id") Long id) {
        Optional<Expert> expertOptional = expertRepository.findById(id);
        if (expertOptional.isPresent()) {
            expertRepository.deleteById(id);
            return ResponseEntity.ok("Expert deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expert not found.");
        }
    }
}
