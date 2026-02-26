package com.smartContact.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Service class for password encryption and validation.
 * Uses BCrypt algorithm for secure password hashing.
 */
@Service
public class PasswordEncryptionService {
    
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    
    /**
     * Encrypts plain text password using BCrypt
     * @param password Plain text password
     * @return Encrypted password
     */
    public String encryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
    
    /**
     * Validates if raw password matches encrypted password
     * @param rawPassword Plain text password
     * @param encodedPassword Encrypted password from database
     * @return True if password matches, false otherwise
     */
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
