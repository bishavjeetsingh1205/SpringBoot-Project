package com.smartContact.service;

import com.smartContact.error.UserNotFoundException;
import com.smartContact.model.User;
import com.smartContact.repository.UserDao;

import java.util.List;

/**
 * Service interface for User management operations
 */
public interface UserService {

    /**
     * Save a new user
     */
    User saveUser(User user);

    /**
     * Fetch all users
     */
    List<User> fetchUserList();

    /**
     * Fetch user by ID
     */
    User fetchUserById(int id) throws UserNotFoundException;

    /**
     * Delete user by ID
     */
    void deleteUser(int id);

    /**
     * Update user information
     */
    void updateUser(int id, User user);

    /**
     * Get user by name
     */
    User getUserNameByName(String name);
    
    /**
     * Get user by email
     */
    User getUserByEmail(String email);
    
    /**
     * Search users by name containing
     */
    List<User> searchUsersByName(String name);
    
    /**
     * Get users by role
     */
    List<User> getUsersByRole(String role);
    
    /**
     * Get users by city
     */
    List<User> getUsersByCity(String city);
    
    /**
     * Get users by country
     */
    List<User> getUsersByCountry(String country);
    
    /**
     * Verify user login credentials
     */
    boolean verifyUserLogin(String email, String password);
    
    /**
     * Check if email already exists
     */
    boolean isEmailExists(String email);
}