package com.hooware.userinfo.service.impl;

import com.hooware.userinfo.dao.UserDao;
import com.hooware.userinfo.dao.impl.UserDaoImpl;
import com.hooware.userinfo.domain.Administrator;
import com.hooware.userinfo.domain.User;
import com.hooware.userinfo.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    /**
     * 查询所有用户数据信息
     */
    @Override
    public List<User> findAllUSer() {
        return userDao.findAll();
    }

    /**
     * 验证用户登陆信息
     * @param login_admin
     * @return
     */
    @Override
    public Administrator findAdmin(Administrator login_admin) {
        return userDao.findAdmin(login_admin);
    }

    /**
     * 添加用户信息
     */
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    /**
     * 根据用户的id删除此用户
     * @param id
     */
    @Override
    public void deleteUserById(String id) {
        userDao.deleteUserById(Integer.parseInt(id));
    }
}
