package com.hooware.userinfo.service.impl;

import com.hooware.userinfo.dao.UserDao;
import com.hooware.userinfo.dao.impl.UserDaoImpl;
import com.hooware.userinfo.domain.Administrator;
import com.hooware.userinfo.domain.PageBean;
import com.hooware.userinfo.domain.User;
import com.hooware.userinfo.service.UserService;

import java.util.List;
import java.util.Map;

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

    /**
     * 更新用户信息之前进行待修改信息的回显
     */
    @Override
    public User searchUserInfoById(String id) {
        return userDao.searchUserInfoById(Integer.parseInt(id));
    }

    /**
     * 根据ID修改用户信息
     *
     */
    @Override
    public void updateUserInfoByID(User user) {
        userDao.updateUserInfoByID(user);
    }

    /**
     * 进行删除选中操作
     * @param ids
     */
    @Override
    public void deleteSelectedUser(String[] ids) {
        for (String id : ids) {
            userDao.deleteUserById(Integer.parseInt(id));
        }
    }

    /**
     * 进行分页查询
     * @param _currentPage
     * @param _rows
     * @param searchMap
     * @return
     */
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> searchMap) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        // 1.new 一个pageBean
        PageBean<User> pageBean = new PageBean<User>();
        // 2.设置参数
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
        // 3.查询总记录数totalCount
        int totalCount = userDao.searchTotalCount(searchMap);
        pageBean.setTotalCount(totalCount);
        // 4.计算总页数totalPage
        int totalPage = (int) Math.ceil((totalCount * 1.0) / rows);
        pageBean.setTotalPage(totalPage);
        // 5.查询List数据集合并封装数据 + 复杂查询
        int index = (currentPage - 1) * rows;
        List<User> list = userDao.findUserByPage(index, rows, searchMap);
        pageBean.setList(list);

        return pageBean;
    }
}
