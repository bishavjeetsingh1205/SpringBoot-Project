package com.smartContact.service;
import com.smartContact.model.User;
import com.smartContact.repository.UserDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImpTest {
    @Autowired
    private UserServiceImp userServiceImp;
    @BeforeAll
    void setUp(){
        User user=User.builder().name("hemraj").email("hemrajmalhi1234@gmail.com").about("Sr").password("root").role("java developer").id(3).build();
        Mockito.when(userServiceImp.getUserNameByName("hemraj")).thenReturn(user);
    }

    @Test
    public void WhenValidDepartmentName_ThenUserShouldBeFound(){
        String name="hemraj";
        User found=userServiceImp.getUserNameByName(name);

        assertEquals(name,found.getName());
    }
}