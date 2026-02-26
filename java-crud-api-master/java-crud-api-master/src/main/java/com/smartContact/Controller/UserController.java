package com.smartContact.Controller;
import com.smartContact.error.UserNotFoundException;
import com.smartContact.model.User;
import com.smartContact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * REST Controller for User management endpoints
 * Handles CRUD operations and additional user queries
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    static Logger logger = Logger.getLogger(UserController.class.getName());
    
    @Autowired
    private UserService userService;

    /**
     * Save a new user
     */
    @PostMapping("/save_user_data")
    public ResponseEntity<Map<String, Object>> saveUser(@Valid @RequestBody User user){
        logger.info("Creating new user with email: " + user.getEmail());
        
        if (userService.isEmailExists(user.getEmail())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Email already exists");
            response.put("success", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
        User savedUser = userService.saveUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User created successfully");
        response.put("user_id", savedUser.getId());
        response.put("success", true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Fetch all users
     */
    @GetMapping("/get_user_data")
    public ResponseEntity<List<User>> fetchUserList(){
        logger.info("Fetching all users");
        List<User> users = userService.fetchUserList();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Fetch user by ID
     */
    @GetMapping("/get_user_data/{id}")
    public ResponseEntity<User> fetchUserById(@PathVariable("id") int id) throws UserNotFoundException {
        logger.info("Fetching user with ID: " + id);
        User user = userService.fetchUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Delete user by ID
     */
    @DeleteMapping("/delete_user_data/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable("id") int id){
        logger.info("Deleting user with ID: " + id);
        userService.deleteUser(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        response.put("success", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Update user information
     */
    @PutMapping("/update_user_data/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable("id") int id, @RequestBody User user){
        logger.info("Updating user with ID: " + id);
        userService.updateUser(id, user);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User updated successfully");
        response.put("success", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get user by name (exact match)
     */
    @GetMapping("/get_user_name/name/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable("name") String name){
        logger.info("Fetching user by name: " + name);
        User user = userService.getUserNameByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    /**
     * Get user by email
     */
    @GetMapping("/get_user_by_email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
        logger.info("Fetching user by email: " + email);
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    /**
     * Search users by name containing keyword
     */
    @GetMapping("/search_users/{name}")
    public ResponseEntity<List<User>> searchUsersByName(@PathVariable("name") String name){
        logger.info("Searching users with name containing: " + name);
        List<User> users = userService.searchUsersByName(name);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    /**
     * Get users by role
     */
    @GetMapping("/get_users_by_role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable("role") String role){
        logger.info("Fetching users by role: " + role);
        List<User> users = userService.getUsersByRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    /**
     * Get users by city
     */
    @GetMapping("/get_users_by_city/{city}")
    public ResponseEntity<List<User>> getUsersByCity(@PathVariable("city") String city){
        logger.info("Fetching users by city: " + city);
        List<User> users = userService.getUsersByCity(city);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    /**
     * Get users by country
     */
    @GetMapping("/get_users_by_country/{country}")
    public ResponseEntity<List<User>> getUsersByCountry(@PathVariable("country") String country){
        logger.info("Fetching users by country: " + country);
        List<User> users = userService.getUsersByCountry(country);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    /**
     * User login endpoint - Verify credentials
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, String> credentials){
        String email = credentials.get("email");
        String password = credentials.get("password");
        
        logger.info("Login attempt for email: " + email);
        
        boolean isValidLogin = userService.verifyUserLogin(email, password);
        Map<String, Object> response = new HashMap<>();
        
        if (isValidLogin) {
            User user = userService.getUserByEmail(email);
            response.put("message", "Login successful");
            response.put("user_id", user.getId());
            response.put("user_name", user.getName());
            response.put("success", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Invalid email or password");
            response.put("success", false);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
    
    /**
     * Check if email exists
     */
    @GetMapping("/check_email/{email}")
    public ResponseEntity<Map<String, Object>> checkEmailExists(@PathVariable("email") String email){
        logger.info("Checking if email exists: " + email);
        boolean exists = userService.isEmailExists(email);
        Map<String, Object> response = new HashMap<>();
        response.put("email", email);
        response.put("exists", exists);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
