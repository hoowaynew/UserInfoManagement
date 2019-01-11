package com.hooware.userinfo.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DataSource dataSource;

    static {
        Properties pro = new Properties();
        InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            pro.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取连接池
    public static DataSource getDataSource() {
        return dataSource;
    }

    // 获取连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
