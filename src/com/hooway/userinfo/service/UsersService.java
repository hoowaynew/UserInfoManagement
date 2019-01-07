package com.hooway.userinfo.service;

import com.hooway.userinfo.domain.Administrator;
import com.hooway.userinfo.domain.PageBean;
import com.hooway.userinfo.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 查询所有用户service层接口
 */

public interface UsersService {
    /**
     * 查询所有用户信息
     * @return
     */
    List<User> findAll();

    /**
     * 用户登录校验
     * @param login_administrator
     * @return
     */
    Administrator login(Administrator login_administrator);

    /**
     * 添加用户
     * @param add_user
     */
    void addUser(User add_user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(String id);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    User queryUserById(String id);

    /**
     * 根据ID更新用户信息用户
     * @param user
     */
    void updateUserById(User user);

    /**
     * 批量删除用户
     * @param ids
     */
    void deleteSelectedUser(String[] ids);

    /**
     * 分页复杂查询用户数据
     * @param currentPage
     * @param rows
     * @param searchMap
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> searchMap);
}
