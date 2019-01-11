package com.hooware.userinfo.dao;

import com.hooware.userinfo.domain.Administrator;
import com.hooware.userinfo.domain.User;

import java.util.List;
import java.util.Map;

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

    /**
     * 更新用户信息之前进行待修改信息的回显
     */
    User searchUserInfoById(int id);

    /**
     * 根据ID修改用户信息
     *
     */
    void updateUserInfoByID(User user);



    /**
     * 查询数据库总记录数
     * @return
     * @param searchMap
     */
    int searchTotalCount(Map<String, String[]> searchMap);

    /**
     * 进行分页查询
     * @param index
     * @param rows
     * @param searchMap
     * @return
     */
    List<User> findUserByPage(int index, int rows, Map<String, String[]> searchMap);
}
