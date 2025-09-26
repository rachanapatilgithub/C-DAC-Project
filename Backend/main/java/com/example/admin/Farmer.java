package com.example.admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Date of birth is required")
    private String dob;

    @NotBlank(message = "Mobile number is required")
    private String mobile;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Aadhaar is required")
    private String aadhaar;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "District is required")
    private String district;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Pin code is required")
    private String pinCode;

    @NotBlank(message = "Land area is required")
    private String landArea;

    @NotBlank(message = "Land type is required")
    private String landType;

    @NotBlank(message = "Soil type is required")
    private String soilType;

    @NotBlank(message = "Farming category is required")
    private String farmingCategory;

    @ElementCollection
    @CollectionTable(name = "farmer_crops", joinColumns = @JoinColumn(name = "farmer_id"))
    @Column(name = "crop")
    private List<String> crops;

    @ElementCollection
    @CollectionTable(name = "farmer_livestock", joinColumns = @JoinColumn(name = "farmer_id"))
    @Column(name = "livestock")
    private List<String> livestock;

    @NotBlank(message = "Password is required")
    private String password;

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public String getLandType() {
        return landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getFarmingCategory() {
        return farmingCategory;
    }

    public void setFarmingCategory(String farmingCategory) {
        this.farmingCategory = farmingCategory;
    }

    public List<String> getCrops() {
        return crops;
    }

    public void setCrops(List<String> crops) {
        this.crops = crops;
    }

    public List<String> getLivestock() {
        return livestock;
    }

    public void setLivestock(List<String> livestock) {
        this.livestock = livestock;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
