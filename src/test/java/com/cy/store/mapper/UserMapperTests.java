package com.cy.store.mapper;

import com.cy.store.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user = new User();
        user.setUsername("lzx");
        user.setPassword("123456");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("lzx");
        System.out.println(user.toString());
    }

    @Test
    public void updatePasswordByUid(){
        Integer rows = userMapper.updatePasswordByUid(2,"114514","管理员",new Date());
        System.out.println(rows);
    }

    @Test
    public void findByUid(){
        User user = userMapper.findByUid(4);
        System.out.println(user.toString());
    }

    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(4);
        user.setPhone("123456789");
        user.setEmail("1111@163.com");
        user.setGender(1);
        Integer rows = userMapper.updateInfoByUid(user);
        System.out.println(rows);
    }

    @Test
    public void updateAvatarById(){
        Integer rows = userMapper.updateAvatarByUid(4,"E:/图片/641.jpg","喵喵",new Date());
        System.out.println(rows);
    }

}
