package com.cy.store.service;

import com.cy.store.pojo.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("eihei");
            user.setPassword("123456");
            userService.reg(user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){

        User user = userService.login("喵", "12356");
        System.out.println(user.toString());
    }

    @Test
    public void changePassword(){
        userService.changePassword(4,"喵喵","123456","114514");
    }

    @Test
    public void changeInfo(){

        User user = new User();
        user.setUsername("喵喵");
        user.setPhone("123456");
        user.setEmail("11111@123.com");
        user.setGender(1);
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        userService.changeInfo(4, user.getUsername(), user);

    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(2,"1123","wuhu");
    }


}
