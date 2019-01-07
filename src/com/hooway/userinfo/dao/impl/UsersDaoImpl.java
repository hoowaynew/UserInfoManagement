package com.hooway.userinfo.dao.impl;

import com.hooway.userinfo.dao.UsersDao;
import com.hooway.userinfo.domain.Administrator;
import com.hooway.userinfo.domain.User;
import com.hooway.userinfo.util.DataSourceUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("All")
public class UsersDaoImpl implements UsersDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtil.getDataSource());

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public List<User> findAllDao() {
        String sql = "select * from users";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    /**
     * 用户登录校验
     *
     * @param login_administrator
     * @return
     */
    @Override
    public Administrator login(Administrator login_administrator) {
        String sql = "select * from administrator where username = ? and password = ?";
        try {
            Administrator administrator = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Administrator>(Administrator.class),
                    login_administrator.getUsername(), login_administrator.getPassword());
            return administrator;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加用户
     *
     * @param add_user
     */
    @Override
    public void add(User add_user) {
        String sql = "insert into users values(null,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, add_user.getName(), add_user.getGender(),
                add_user.getAge(), add_user.getAddress(), add_user.getQq(), add_user.getEmail());
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        String sql = "delete from users where id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @Override
    public User queryById(int id) {
        String sql = "select * from users where id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据ID更新用户信息用户
     *
     * @param user
     */
    @Override
    public void updateById(User user) {
        String sql = "update users set name= ?, gender = ?, age = ?, address = ?, qq = ?, email = ? where id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    /**
     * 查询数据库所有记录数
     *
     * @param searchMap
     * @return
     */
    @Override
    public int findTotalCount(Map<String, String[]> searchMap) {
        String sql = "select count(*) from users where 1 = 1  ";
        StringBuilder sb = new StringBuilder(sql);  // 保存查询sql语句
        ArrayList<String> list = new ArrayList<>(); // 保存查询的值
        for (String key : searchMap.keySet()) {
            String value = searchMap.get(key)[0];
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            if (value != null || !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                list.add("%" + value + "%");
            }
        }
        sql = sb.toString();
//        System.out.println(sql);
//        System.out.println(list);
        return jdbcTemplate.queryForObject(sql, Integer.class, list.toArray());
    }

    /**
     * 分页查询数据并返回List<USer>集合
     *
     * @param startIndex
     * @param rows
     * @param searchMap
     * @return
     */
    @Override
    public List<User> findUserByPage(int startIndex, int rows, Map<String, String[]> searchMap) {
        String sql = "select * from users where 1 = 1  ";
        StringBuilder sb = new StringBuilder(sql);  // 保存查询sql语句
        ArrayList list = new ArrayList(); // 保存查询的值
        for (String key : searchMap.keySet()) {
            String value = searchMap.get(key)[0];
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            if (value != null || !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                list.add("%" + value + "%");
            }
        }
        sb.append(" limit ?, ? ");
        list.add(startIndex);
        list.add(rows);
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(list);

//        sb.append(" limit " + startIndex +",  " + rows);
//        sql = sb.toString();

//        System.out.println(sql);
//        System.out.println("rows = " + rows);
//        System.out.println(list);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), list.toArray());
    }
}
