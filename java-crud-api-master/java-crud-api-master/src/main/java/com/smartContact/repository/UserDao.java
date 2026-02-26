package com.smartContact.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartContact.model.User;
import org.springframework.stereotype.Repository;



@Repository
public interface UserDao extends JpaRepository<User,Integer>{
    public User findByName(String name);
}
