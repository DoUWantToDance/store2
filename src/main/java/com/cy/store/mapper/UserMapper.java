package com.cy.store.mapper;

import com.cy.store.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.Date;

/** 用户模块持久层接口 */

public interface UserMapper {

    /**
     * 插入用户数据
     * @param user 用户数据
     * @return 受影响行数
     */
    Integer insert(User user);

    /**
     * 根据用户名查询
     * @param username 用户名
     * @return 找到返回用户数据，未找到返回null
     */
    User findByUsername(String username);

    /**
     * 根据uid修改密码
     * @param uid
     * @param password 密码
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 受影响行数
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据uid查询
     * @param uid
     * @return
     */
    User findByUid(Integer uid);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    Integer updateInfoByUid(User user);

    /**
     * 更改头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid(Integer uid, String avatar, String modifiedUser, Date modifiedTime);


}
