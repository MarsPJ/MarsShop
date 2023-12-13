package com.marsshop.dao.impl;

import com.marsshop.dao.BaseDao;
import com.marsshop.dao.EmailSetDao;
import com.marsshop.domain.EmailSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailSetDaoImpl extends BaseDao implements EmailSetDao {

    @Override
    public void save(EmailSet emailSet) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "insert into emailset (emServer, emAddress, emPassCode) values " +
                    "(?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, emailSet.getEmServer());
            ps.setString(2, emailSet.getEmAddress());
            ps.setString(3, emailSet.getEmPassCode());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
    }

    @Override
    public void update(EmailSet emailSet) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "update emailset set emServer = ?, emAddress = ?, emPassCode = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, emailSet.getEmServer());
            ps.setString(2, emailSet.getEmAddress());
            ps.setString(3, emailSet.getEmPassCode());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
    }

    @Override
    public EmailSet select() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        EmailSet emailSet = null;

        try {
            conn = getConn();
            String sql = "select emID, emServer, emAddress, emPassCode from emailset";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                emailSet = new EmailSet();
                emailSet.setEmId(rs.getInt("emID"));
                emailSet.setEmServer(rs.getString("emServer"));
                emailSet.setEmAddress(rs.getString("emAddress"));
                emailSet.setEmPassCode(rs.getString("emPassCode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return emailSet;
    }
}
