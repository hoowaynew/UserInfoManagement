package com.hooway.userinfo.service.impl;

import com.hooway.userinfo.dao.UsersDao;
import com.hooway.userinfo.dao.impl.UsersDaoImpl;
import com.hooway.userinfo.domain.Administrator;
import com.hooway.userinfo.domain.PageBean;
import com.hooway.userinfo.domain.User;
import com.hooway.userinfo.service.UsersService;

import java.util.List;
import java.util.Map;

public class UsersServiceImpl implements UsersService {
    private UsersDao usersDao = new UsersDaoImpl();

    // 调用DAO层查询所有用户
    @Override
    public List<User> findAll() {
        return usersDao.findAllDao();
    }

    // 调用DAO层校验用户登录
    @Override
    public Administrator login(Administrator login_administrator) {

        return usersDao.login(login_administrator);
    }

    // 调用DAO层添加用户
    @Override
    public void addUser(User add_user) {
        usersDao.add(add_user);
    }

    /**
     * 调用DAO层删除用户
     * @param id
     */
    @Override
    public void deleteUser(String id) {
        usersDao.delete(Integer.parseInt(id));
    }

    /**
     *  调用DAO层根据ID查询用户
     * @param id
     * @return
     */
    @Override
    public User queryUserById(String id) {
        return usersDao.queryById(Integer.parseInt(id));
    }

    /**
     *  调用DAO层根据ID更新用户信息用户
     * @param user
     */
    @Override
    public void updateUserById(User user) {
        usersDao.updateById(user);
    }

    @Override
    public void deleteSelectedUser(String[] ids) {
        if (ids != null && ids.length !=0) {
            for (String id : ids) {
                usersDao.delete(Integer.parseInt(id));
            }
        }
    }

    /**
     * 调用DAO层分页查询用户数据
     * @param strCurrentPage
     * @param strRows
     * @param searchMap
     * @return
     */
    @Override
    public PageBean<User> findUserByPage(String strCurrentPage, String strRows, Map<String, String[]> searchMap) {
        // 1.生成pageBean对象和转换参数
        int currentPage = Integer.parseInt(strCurrentPage);
        int rows = Integer.parseInt(strRows);
        PageBean<User> userPageBean = new PageBean<>();
        // 2.封装数据currentPage,rows
        userPageBean.setCurrentPage(currentPage);
        userPageBean.setRows(rows);
        // 3.查询数据总数并设置totalCount
        int totalCount = usersDao.findTotalCount(searchMap);
        userPageBean.setTotalCount(totalCount);
        // 4.查询分页的页面总数并设置pageCount
        int pageCount = (int) Math.ceil(totalCount * 1.0 / rows);
        userPageBean.setPageCount(pageCount);
        // 5.0.分页查询的起始索引和页面条目数
        int startIndex = (currentPage - 1) * rows;
        // 5.1.查询分页的页面数据集合并设置List<User>
        List<User>  list = usersDao.findUserByPage(startIndex, rows, searchMap);
        userPageBean.setList(list);
        System.out.println(list);

        return userPageBean;
    }
}
