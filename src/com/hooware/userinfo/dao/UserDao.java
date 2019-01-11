package com.hooware.userinfo.dao;

import com.hooware.userinfo.domain.Administrator;
import com.hooware.userinfo.domain.User;

import java.util.List;

/**
 * Dao层,用于与数据库进行增删改查
 */
public interface UserDao {
    /**
     * 查询所有用户信息
     */
    List<User> findAll();

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
    void deleteUserById(int id);
}
