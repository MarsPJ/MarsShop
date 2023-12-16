package com.marsshop.dao;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class BaseDao {
    // 驱动类名
    private static final String DRIVER_DEVICE_NAME = "com.mysql.cj.jdbc.Driver";
    // 连接字符串
    private static final String URL = "jdbc:mysql://localhost:3306/marsshop?characterEncoding=utf-8&serverTimezone=UTC";
    // 连接用户名
    private static String DB_NAME = "root";
    // 连接密码
    private static String DB_PWD = "123456";
    // 加载mysql驱动类
    static {
        try {
            Class.forName(DRIVER_DEVICE_NAME);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("mysql数据库驱动加载失败！");
        }
    }

    /**
     * 创建连接
     * @return
     * @throws SQLException
     */
    protected Connection getConn() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, DB_NAME, DB_PWD);
        return conn;
    }

    /**
     * 关闭连接，释放资源
     * @param con
     * @param stat
     * @param rs
     */
    protected void close(Connection con, Statement stat, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
