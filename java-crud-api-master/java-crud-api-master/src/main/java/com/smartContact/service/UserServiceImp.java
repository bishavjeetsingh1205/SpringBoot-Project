package com.smartContact.service;
import com.smartContact.error.UserNotFoundException;
import com.smartContact.model.User;
import com.smartContact.repository.UserDao;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Implementation of UserService interface
 * Handles all business logic for User management operations
 */
@Service
public class UserServiceImp implements UserService {
    
    static Logger logger = Logger.getLogger(UserServiceImp.class.getName());
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private PasswordEncryptionService passwordEncryptionService;
    
    @Override
    public User saveUser(User user) {
        logger.info("Saving user with email: " + user.getEmail());
        // Encrypt password before saving
        user.setPassword(passwordEncryptionService.encryptPassword(user.getPassword()));
        if (user.getStatus() == null) {
            user.setStatus("ACTIVE");
        }
        return userDao.save(user);
    }

    @Override
    public List<User> fetchUserList() {
        logger.info("Fetching all users");
        return userDao.findAll();
    }

    @Override
    public User fetchUserById(int id) throws UserNotFoundException {
        logger.info("Fetching user with ID: " + id);
        Optional<User> user = userDao.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        return user.get();
    }

    @Override
    public void deleteUser(int id) {
        logger.info("Deleting user with ID: " + id);
        userDao.deleteById(id);
    }

    @Override
    public void updateUser(@NotNull int id, User user) {
        logger.info("Updating user with ID: " + id);
        user.setId(id);
        // Only encrypt password if it has changed (not already encrypted)
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a")) {
            user.setPassword(passwordEncryptionService.encryptPassword(user.getPassword()));
        }
        userDao.save(user);
    }
    
    @Override
    public User getUserNameByName(String name) {
        logger.info("Fetching user by name: " + name);
        return userDao.findByName(name);
    }
    
    @Override
    public User getUserByEmail(String email) {
        logger.info("Fetching user by email: " + email);
        return userDao.findByEmail(email);
    }
    
    @Override
    public List<User> searchUsersByName(String name) {
        logger.info("Searching users by name containing: " + name);
        return userDao.findByNameContainingIgnoreCase(name);
    }
    
    @Override
    public List<User> getUsersByRole(String role) {
        logger.info("Fetching users by role: " + role);
        return userDao.findByRole(role);
    }
    
    @Override
    public List<User> getUsersByCity(String city) {
        logger.info("Fetching users by city: " + city);
        return userDao.findByCity(city);
    }
    
    @Override
    public List<User> getUsersByCountry(String country) {
        logger.info("Fetching users by country: " + country);
        return userDao.findByCountry(country);
    }
    
    @Override
    public boolean verifyUserLogin(String email, String password) {
        logger.info("Verifying login for email: " + email);
        User user = userDao.findByEmail(email);
        if (user == null) {
            return false;
        }
        return passwordEncryptionService.validatePassword(password, user.getPassword());
    }
    
    @Override
    public boolean isEmailExists(String email) {
        logger.info("Checking if email exists: " + email);
        return userDao.existsByEmail(email);
    }
}






