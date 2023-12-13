package com.marsshop.dao.impl;

import com.marsshop.dao.BaseDao;
import com.marsshop.dao.OrderDao;
import com.marsshop.domain.Order;
import com.marsshop.domain.OrderSelectParam;
import com.marsshop.domain.User;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int selectCountByParam(OrderSelectParam param) {
        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        int count = 0;

        try {
            conn = getConn();
            StringBuilder sql = new StringBuilder("select count(*) from `order` o, user u" +
                    " where o.uid = u.uid");
            List<Object> pars = new ArrayList<>();
            if (param.getOcode() != null && !param.getOcode().isEmpty()) {
                sql.append(" and o.oCode like ?");
                pars.add("%" + param.getOcode() + "%");
            }
            if (param.getUname() != null && !param.getUname().isEmpty()) {
                sql.append(" and u.uname like ?");
                pars.add("%" + param.getUname() + "%");
            }
            if (param.getOtimeBegin() != null) {
                sql.append(" and o.otime >= ?");
                pars.add(param.getOtimeBegin());
            }
            if (param.getOtimeEnd() != null) {
                sql.append(" and o.otime <= ?");
                pars.add(param.getOtimeEnd());
            }
            if (param.getOstatus() != null) {
                sql.append(" and o.ostatus = ?");
                pars.add(param.getOstatus());
            }

            ps = conn.prepareStatement(sql.toString());
            for (int i = 0; i < pars.size(); i++) {
                ps.setObject(i + 1, pars.get(i));
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
    public List<Order> selectByParam(OrderSelectParam param, Integer pageIndex, Integer pageSize) {
        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        Order order = null;
        List<Order> orderList = new ArrayList<>();

        try {
            conn = getConn();
            StringBuilder sql = new StringBuilder("select oid, o.uid, oCode, oTime, oTotal, oStatus," +
                    " u.uname" +
                    " from `order` o, user u" +
                    " where o.uid = u.uid");
            List<Object> pars = new ArrayList<>();
            if (param.getOcode() != null && !param.getOcode().isEmpty()) {
                sql.append(" and o.oCode like ?");
                pars.add("%" + param.getOcode() + "%");
            }
            if (param.getUname() != null && !param.getUname().isEmpty()) {
                sql.append(" and u.uname like ?");
                pars.add("%" + param.getUname() + "%");
            }
            if (param.getOtimeBegin() != null) {
                sql.append(" and o.otime >= ?");
                pars.add(param.getOtimeBegin());
            }
            if (param.getOtimeEnd() != null) {
                sql.append(" and o.otime <= ?");
                pars.add(param.getOtimeEnd());
            }
            if (param.getOstatus() != null) {
                sql.append(" and o.ostatus = ?");
                pars.add(param.getOstatus());
            }
            sql.append(" order by ostatus, otime limit ?, ?");
            pars.add(pageIndex);
            pars.add(pageSize);
            ps = conn.prepareStatement(sql.toString());
            for (int i = 0; i < pars.size(); i++) {
                ps.setObject(i + 1, pars.get(i));
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setOid(rs.getInt("oid"));

                User u = new User();
                u.setUid(rs.getInt("uid"));
                u.setUname(rs.getString("uname"));
                order.setUser(u);

                order.setOcode(rs.getString("ocode"));
                order.setOtime(rs.getTimestamp("otime"));
                order.setOtotal(rs.getBigDecimal("ototal"));
                order.setOstatus(rs.getInt("ostatus"));
                orderList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return orderList;
    }

    @Override
    public void updateStatus(Integer ostatus, Integer oid) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "update `order` set oStatus = ? where oID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ostatus);
            ps.setInt(2, oid);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
    }
}
