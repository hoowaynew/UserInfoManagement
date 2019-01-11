package com.hooware.userinfo.service;

import com.hooware.userinfo.domain.Administrator;
import com.hooware.userinfo.domain.User;

import java.util.List;

/**
 *  Service层,提供用户服务
 */
public interface UserService {
    /**
     * 查询所有用户数据信息
     */
    List<User> findAllUSer();

    /**
     * 验证用户登陆信息
     * @param login_admin
     * @return
     */
    Administrator findAdmin(Administrator login_admin);

    /**
     * 添加用户信息
     */
    void addUser(User user);

    /**
     * 根据用户的id删除此用户
     * @param id
     */
    void deleteUserById(String id);
}
