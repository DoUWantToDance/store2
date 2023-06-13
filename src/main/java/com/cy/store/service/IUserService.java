package com.cy.store.service;

import com.cy.store.pojo.User;

/** 用户模块业务层接口 */
public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户数据
     */
    void reg(User user);

    /**
     * 用户登录方法
     * @param username 用户名
     * @param password 密码
     * @return 用户数据
     */
    User login(String username, String password);

    /**
     * 修改密码
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    void changePassword(Integer uid, String username, String oldPassword, String newPassword);

    /**
     * 通过uid查询
     * @param uid
     * @return
     */
    User getByUid(Integer uid);

    /**
     * 修改用户信息
     * @param uid
     * @param username
     * @param user
     */
    void changeInfo(Integer uid, String username, User user);

    /**
     * 更新用户头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     */
    void changeAvatar(Integer uid, String avatar, String username);

}
