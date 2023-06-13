package com.cy.store.service.impl;

import com.cy.store.mapper.UserMapper;
import com.cy.store.pojo.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/** 用户业务层模块实现类 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void reg(User user) {
        User result = userMapper.findByUsername(user.getUsername());
        if (result!=null) {
           throw new UsernameDuplicatedException("用户名被占用！");
        }

        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMD5Password(oldPassword,salt);

        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        Integer rows = userMapper.insert(user);
        if (rows!=1) {
            throw new InsertException("发生未知异常！");
        }
    }

    @Override
    public User login(String username, String password) {

        User result = userMapper.findByUsername(username);
        if (result==null || result.getIsDelete()==1) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        String oldPassword = result.getPassword();
        String salt = result.getSalt();
        String md5Password = getMD5Password(password, salt);
        if (!md5Password.equals(oldPassword)) {
            throw new PasswordNotMatchException("密码错误！");
        }

        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        return user;
    }

    @Override
    @Transactional
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {

        User result = userMapper.findByUid(uid);
        if(result==null || result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户不存在！");
        }
        String md5Password = getMD5Password(oldPassword, result.getSalt());
        if(!md5Password.equals(result.getPassword())){
            throw new PasswordNotMatchException("密码输入错误！");
        }
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if(rows!=1){
            throw new UpdateException("密码修改时发生异常！");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if(result ==null || result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    @Transactional
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if(result ==null || result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setUsername(username);
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if(rows!=1){
            throw new UpdateException("更新数据时发生异常！");
        }
    }

    @Override
    @Transactional
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result = userMapper.findByUid(uid);
        if(result ==null || result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid,avatar,username,new Date());
        if(rows!=1){
            throw new UpdateException("更新数据时发生异常！");
        }
    }


    private String getMD5Password(String password, String salt) {

        for (int i=1 ; i<3 ; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

}
