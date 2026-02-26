package com.smartContact.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.smartContact.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserDao extends JpaRepository<User,Integer>{
    
    /**
     * Find user by name
     */
    User findByName(String name);
    
    /**
     * Find user by email
     */
    User findByEmail(String email);
    
    /**
     * Find users by role
     */
    List<User> findByRole(String role);
    
    /**
     * Find users by city
     */
    List<User> findByCity(String city);
    
    /**
     * Find users by status
     */
    List<User> findByStatus(String status);
    
    /**
     * Search users by name containing (case-insensitive)
     */
    List<User> findByNameContainingIgnoreCase(String name);
    
    /**
     * Custom query to find users by country
     */
    @Query("SELECT u FROM User u WHERE u.country = :country")
    List<User> findByCountry(@Param("country") String country);
    
    /**
     * Check if user exists by email
     */
    boolean existsByEmail(String email);
    
    /**
     * Find users by role and status
     */
    @Query("SELECT u FROM User u WHERE u.role = :role AND u.status = :status")
    List<User> findByRoleAndStatus(@Param("role") String role, @Param("status") String status);
}
