package com.smartContact.service;
import com.smartContact.error.UserNotFoundException;
import com.smartContact.model.User;
import com.smartContact.repository.UserDao;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }


    @Override
    public List<User> fetchUserList() {
        return userDao.findAll();
    }

    @Override
    public User fetchUserById(int id) throws UserNotFoundException {
        Optional<User> user= userDao.findById(id);
        if (!user.isPresent()){
            throw new UserNotFoundException("User are not available");
        }
        return user.get();
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteById(id);
    }

    @Override
    public void updateUser(@NotNull int id, User user) {
        user.setId(id);
        userDao.save(user);

    }
    public User getUserNameByName(String name){
        return userDao.findByName(name);
        }


    }






