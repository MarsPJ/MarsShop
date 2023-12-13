package com.marsshop.dao.impl;

import com.marsshop.dao.BaseDao;
import com.marsshop.dao.UserDao;
import com.marsshop.domain.Goods;
import com.marsshop.domain.User;
import org.apache.commons.fileupload.RequestContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public List<User> selectByParam(String param, int firstIndex, int pageSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<User> userList = new ArrayList<>();
        User user = null;
        try {
            conn = getConn();
            String sql = "select uID, uname, uPwd, uSex, uBirth, uPhone, uEmail, uQQ, uImage, uCredit, uRegTime from user";
            if (param != null && !param.isEmpty()) {
                sql += " where uname like ? or uPhone like ?";
            }
            sql += " order by uregTime desc LIMIT ? , ?";
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (param != null && !param.isEmpty()) {
                ps.setString(index++, "%" + param + "%");
                ps.setString(index++, "%" + param + "%");
            }
            ps.setInt(index++, firstIndex);
            ps.setInt(index, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUid(rs.getInt("uid"));
                user.setUname(rs.getString("uname"));
                user.setUpwd(rs.getString("upwd"));
                user.setUsex(rs.getString("usex"));
                user.setUbirth(rs.getDate("ubirth"));
                user.setUphone(rs.getString("uphone"));
                user.setUemail(rs.getString("uemail"));
                user.setUqq(rs.getString("uqq"));
                user.setUimage(rs.getString("uimage"));
                user.setUcredit(rs.getInt("ucredit"));
                user.setUregTime(rs.getTimestamp("uregTime"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return userList;
    }

    @Override
    public int selectCountByParam(String param) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = getConn();
            String sql = "select count(*) from user";
            if (param != null && !param.isEmpty()) {
                sql += " where uname like ? or uPhone like ?";
            }
            ps = conn.prepareStatement(sql);
            if (param != null && !param.isEmpty()) {
                ps.setString(1, "%" + param + "%");
                ps.setString(2, "%" + param + "%");
            }
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return count;
    }

    @Override
    public User selectByUid(Integer uid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = getConn();
            String sql = "select uID, uname, uPwd, uSex, uBirth, uPhone, uEmail, uQQ, uImage, uCredit, uRegTime from user where uid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUid(rs.getInt("uid"));
                user.setUname(rs.getString("uname"));
                user.setUpwd(rs.getString("upwd"));
                user.setUsex(rs.getString("usex"));
                user.setUbirth(rs.getDate("ubirth"));
                user.setUphone(rs.getString("uphone"));
                user.setUemail(rs.getString("uemail"));
                user.setUqq(rs.getString("uqq"));
                user.setUimage(rs.getString("uimage"));
                user.setUcredit(rs.getInt("ucredit"));
                user.setUregTime(rs.getTimestamp("uregTime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return user;
    }

    @Override
    public boolean existByUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean resutl = false;
        try {
            conn = getConn();
            String sql = "select count(*) from user where 1 = 1";
            List<Object> params = new ArrayList<>();
            if(user.getUname() != null && !user.getUname().isEmpty()){
                sql += " and uname = ?";
                params.add(user.getUname());
            }
            if(user.getUphone() != null && !user.getUphone().isEmpty()){
                sql += " and uphone = ?";
                params.add(user.getUphone());
            }

            if(user.getUemail() != null && !user.getUemail().isEmpty()){
                sql += " and uemail = ?";
                params.add(user.getUemail());
            }
            if(user.getUqq() != null && !user.getUqq().isEmpty()){
                sql += " and uqq = ?";
                params.add(user.getUqq());
            }
            // 如果什么条件都没有，查询一定为true，所有必须至少有一个条件
            if (params.size() > 0) {
                ps = conn.prepareStatement(sql);

                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }

                rs = ps.executeQuery();
                rs.next();
                resutl = rs.getInt(1) > 0;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return resutl;
    }

    @Override
    public void save(User user) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "insert into easybuy.user (uname, uPwd, uSex, uBirth, uPhone, " +
                    "uEmail, uQQ ,uRegTime)\n" +
                    "values (?,?,?,?,?,?,?,?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUname());
            ps.setString(2, user.getUpwd());
            ps.setString(3, user.getUsex());
            ps.setDate(4, new Date(user.getUbirth().getTime()));
            ps.setString(5, user.getUphone());
            ps.setString(6, user.getUemail());
            ps.setString(7, user.getUqq());
            ps.setDate(8, new Date(user.getUregTime().getTime()));

            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
    }
}
