package com.hooware.userinfo.dao.impl;

import com.hooware.userinfo.dao.UserDao;
import com.hooware.userinfo.domain.Administrator;
import com.hooware.userinfo.domain.User;
import com.hooware.userinfo.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
     *
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
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    /**
     * 根据用户的id删除此用户
     *
     * @param id
     */
    @Override
    public void deleteUserById(int id) {
        String sql = "delete from users where id = ?";
        template.update(sql, id);
    }

    /**
     * 更新用户信息之前进行待修改信息的回显
     */
    @Override
    public User searchUserInfoById(int id) {
        String sql = "select * from users where id = ?";
        try {
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据ID修改用户信息
     */
    @Override
    public void updateUserInfoByID(User user) {
        String sql = "update users set gender = ?, age = ?, address = ?, qq = ?, email = ? where id = ?";
        template.update(sql, user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    /**
     * 查询数据库总记录数
     *
     * @return
     * @param searchMap
     */
    @Override
    public int searchTotalCount(Map<String, String[]> searchMap) {
        String sql = "select count(*) from users where 1 = 1 ";
//        Integer totalCount = template.queryForObject(sql, new BeanPropertyRowMapper<Integer>(Integer.class));  // wrong
        StringBuilder sb = new StringBuilder(sql);
        List<Object> list = new ArrayList<Object>();
        Set<String> keys = searchMap.keySet();
        for (String key : keys) {
            if("currentPage".equals(key) || "rows".equals(key))
                continue;
            String value = searchMap.get(key)[0];
            if(value != "") {
                sb.append(" and " + key + " like ? ");
                list.add("%" + value + "%");
            }
        }
        sql = sb.toString();
        Integer totalCount = template.queryForObject(sql, Integer.class, list.toArray());
        return totalCount;
    }

    /**
     * 进行分页查询
     *
     * @param index
     * @param rows
     * @param searchMap
     * @return
     */
    @Override
    public List<User> findUserByPage(int index, int rows, Map<String, String[]> searchMap) {
        String sql = "select * from users where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> list = new ArrayList<Object>();
        Set<String> keys = searchMap.keySet();
        for (String key : keys) {
            if("currentPage".equals(key) || "rows".equals(key))
                continue;
            String value = searchMap.get(key)[0];
            if(value != "") {
                sb.append(" and " + key + " like ? ");
                list.add("%" + value + "%");
            }
        }
        sb.append(" limit ?, ?");
        list.add(index);
        list.add(rows);
        sql = sb.toString();

        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), list.toArray());
    }
}
