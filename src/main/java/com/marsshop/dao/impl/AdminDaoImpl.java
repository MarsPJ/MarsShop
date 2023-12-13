package com.marsshop.dao.impl;

import com.marsshop.dao.AdminDao;
import com.marsshop.dao.BaseDao;
import com.marsshop.domain.Admin;

import java.sql.*;
import java.util.Date;

public class AdminDaoImpl extends BaseDao implements AdminDao {


    @Override
    public Admin selectByName(String aname) {
        // 声明JDBC接口
        // 连接
        Connection conn = null;
        // 预编译的 SQL 语句，可以防止sql注入
        PreparedStatement ps = null;
        // 结果集
        ResultSet rs = null;
        // 声明返回值
        Admin admin = null;

        // 处理逻辑
        try {
            conn = getConn();
            String sql = "select aID, aName, aPwd, aLastLogin from admin where aName = ?";
            ps =conn.prepareStatement(sql);
            ps.setString(1, aname);
            rs = ps.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setAid(rs.getInt("aID"));
                admin.setAname(aname);
                admin.setApwd(rs.getString("aPwd"));
                //Timestamp是date的子类，可以赋值
                admin.setaLastLogin(rs.getTimestamp("aLastLogin"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return admin;
    }

    @Override
    public void updateLastLogin(Integer aid, Date aLastLogin) {
        // 声明JDBC接口
        // 连接
        Connection conn = null;
        // 预编译的 SQL 语句，可以防止sql注入
        PreparedStatement ps = null;


        // 处理逻辑
        try {
            conn = getConn();
            String sql = "update admin set aLastLogin = ? where aID = ?";
            ps = conn.prepareStatement(sql);
            //date是Timestamp的父类，不可以赋值
            ps.setTimestamp(1, new Timestamp(aLastLogin.getTime()));
            ps.setInt(2, aid);
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }

    }
}
