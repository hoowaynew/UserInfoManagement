package com.hooway.userinfo.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceUtil {

    static DataSource dataSource;

    static {
        Properties properties = new Properties();
        InputStream is = DataSourceUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource != null)
            return dataSource.getConnection();
        return null;
    }

}
