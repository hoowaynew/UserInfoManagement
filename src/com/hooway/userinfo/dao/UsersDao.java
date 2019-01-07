package com.hooway.userinfo.dao;

import com.hooway.userinfo.domain.Administrator;
import com.hooway.userinfo.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 查询所有用户dao层接口
 */

public interface UsersDao {

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> findAllDao();

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
    void add(User add_user);

    /**
     * 删除用户
     * @param id
     */
    void delete(int id);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    User queryById(int id);

    /**
     *  根据ID更新用户信息用户
     * @param user
     */
    void updateById(User user);

    /**
     * 查询数据库所有记录数
     * @return
     * @param searchMap
     */
    int findTotalCount(Map<String, String[]> searchMap);

    /**
     * 分页复杂查询数据并返回List<USer>集合
     * @param startIndex
     * @param rows
     * @param searchMap
     * @return
     */
    List<User> findUserByPage(int startIndex, int rows, Map<String, String[]> searchMap);
}
