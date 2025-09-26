package com.example.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Expert, Long> {

    // Find Expert by username
    Expert findByUsername(String username);
}
