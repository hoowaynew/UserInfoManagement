package com.hooware.userinfo.dao.impl;

import com.hooware.userinfo.dao.UserDao;
import com.hooware.userinfo.domain.Administrator;
import com.hooware.userinfo.domain.User;
import com.hooware.userinfo.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
    /**
     * 查询所有用户信息
     */
    @Override
    public List<User> findAll() {
        String sql = "select * from users";
        List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return list;
    }

    /**
     * 验证用户登陆信息
     * @param login_admin
     * @return
     */
    @Override
    public Administrator findAdmin(Administrator login_admin) {
        String sql = "select * from administrator where username = ? and password = ?";
        try {
            Administrator admin = template.queryForObject(sql, new BeanPropertyRowMapper<Administrator>(Administrator.class), login_admin.getUsername(), login_admin.getPassword());
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return null;
        }
    }

    /**
     * 添加用户信息
     */
    @Override
    public void addUser(User user) {
        String sql = "insert into users values(null,?,?,?,?,?,?)";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
    }

    /**
     * 根据用户的id删除此用户
     * @param id
     */
    @Override
    public void deleteUserById(int id) {
        String sql = "delete from users where id = ?";
        template.update(sql,id);
    }
}
