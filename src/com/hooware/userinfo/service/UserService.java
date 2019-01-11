package com.hooware.userinfo.service;

import com.hooware.userinfo.domain.Administrator;
import com.hooware.userinfo.domain.PageBean;
import com.hooware.userinfo.domain.User;

import java.util.List;
import java.util.Map;

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

    /**
     * 更新用户信息之前进行待修改信息的回显
     */
    User searchUserInfoById(String id);

    /**
     * 根据ID修改用户信息
     *
     */
    void updateUserInfoByID(User user);

    /**
     * 进行删除选中操作
     * @param ids
     */
    void deleteSelectedUser(String[] ids);

    /**
     * 进行分页查询
     * @param currentPage
     * @param rows
     * @param searchMap
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> searchMap);
}
