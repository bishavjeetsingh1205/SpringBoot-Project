package com.smartContact.Controller;
import com.smartContact.error.UserNotFoundException;
import com.smartContact.model.User;
import com.smartContact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class UserController {
    static Logger logger = Logger.getLogger(UserController.class.getName());
    @Autowired
    private  UserService userService;

    @PostMapping("/save_user_data")
    public ResponseEntity<String> saveUser(@Valid @RequestBody User user){
        logger.info("inside the saveUser of UserController ");
          userService.saveUser(user);
        return new ResponseEntity<>("User data saved successfully!", HttpStatus.OK);
    }

    @GetMapping("/get_user_data")
    public List<User> fetchUserList(){
        logger.info("inside the fetchUserList of UserController ");
        return userService.fetchUserList();
   }

    @GetMapping("/get_user_data/{id}")
    public User fetchUserById(@PathVariable("id") int id) throws UserNotFoundException {
        return userService.fetchUserById(id);
    }

    @DeleteMapping("/delete_user_data/{id}")
    public ResponseEntity<String>deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return new ResponseEntity<>("user data deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("/update_user_data/{id}")
    public User updateUser(@PathVariable("id") int id ,@RequestBody User user){
        this.userService.updateUser(id,user);
        return user;

    }

    @GetMapping("get_user_name/name/{name}")
    public User getUserNameByName(@PathVariable("name") String name){
        return userService.getUserNameByName(name);
    }


}
