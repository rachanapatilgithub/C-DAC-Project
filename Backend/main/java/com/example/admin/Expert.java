package com.example.admin;

import jakarta.persistence.*;

@Entity
@Table(name = "expert")  // ✅ Ensures correct mapping to 'expert' table in the DB
public class Expert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Personal Information
    private String fullName;
    private String gender;
    private String dob;
    private String mobile;
    private String alternatePhone;
    private String email;
    private String aadhaar;

    // Professional Details
    private String qualification;
    private String expertise;          // comma-separated (e.g., "Soil,Crops")
    private int experience;
    private String languages;          // comma-separated
    private String timeSlots;          // comma-separated
    private String consultationModes;  // comma-separated

    // Location
    private String state;
    private String district;
    private String city;
    private String pinCode;

    // Account Setup
    private String username;
    private String password;

    // Getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getAlternatePhone() { return alternatePhone; }
    public void setAlternatePhone(String alternatePhone) { this.alternatePhone = alternatePhone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAadhaar() { return aadhaar; }
    public void setAadhaar(String aadhaar) { this.aadhaar = aadhaar; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public String getExpertise() { return expertise; }
    public void setExpertise(String expertise) { this.expertise = expertise; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public String getLanguages() { return languages; }
    public void setLanguages(String languages) { this.languages = languages; }

    public String getTimeSlots() { return timeSlots; }
    public void setTimeSlots(String timeSlots) { this.timeSlots = timeSlots; }

    public String getConsultationModes() { return consultationModes; }
    public void setConsultationModes(String consultationModes) { this.consultationModes = consultationModes; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPinCode() { return pinCode; }
    public void setPinCode(String pinCode) { this.pinCode = pinCode; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
