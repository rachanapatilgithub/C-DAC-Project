package com.example.admin;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long> {

    // Existing methods
    Expert findByUsername(String username);
    Expert findByUsernameAndPassword(String username, String password);

    // New method for filtering experts
    @Query("SELECT e FROM Expert e " +
           "WHERE (:name = '' OR LOWER(e.fullName) LIKE LOWER(CONCAT('%', :name, '%'))) " +
           "AND (:expertise = '' OR LOWER(e.expertise) LIKE LOWER(CONCAT('%', :expertise, '%'))) " +
           "AND (:location = '' OR LOWER(e.city) LIKE LOWER(CONCAT('%', :location, '%')) " +
           "     OR LOWER(e.district) LIKE LOWER(CONCAT('%', :location, '%')) " +
           "     OR LOWER(e.state) LIKE LOWER(CONCAT('%', :location, '%')))")
    List<Expert> findByFilters(@Param("name") String name,
                               @Param("expertise") String expertise,
                               @Param("location") String location);
}
