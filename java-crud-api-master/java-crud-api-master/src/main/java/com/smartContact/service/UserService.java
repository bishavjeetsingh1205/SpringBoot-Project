package com.smartContact.service;

import com.smartContact.error.UserNotFoundException;
import com.smartContact.model.User;
import com.smartContact.repository.UserDao;

import java.util.List;

public interface UserService {

    public User saveUser(User user);

    public List<User> fetchUserList();


   public User fetchUserById(int id) throws UserNotFoundException;

    public void deleteUser(int id);

   public void updateUser(int id, User user);

  public User getUserNameByName(String name);
}