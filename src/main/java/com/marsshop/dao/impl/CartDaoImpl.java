package com.marsshop.dao.impl;

import com.marsshop.dao.BaseDao;
import com.marsshop.dao.CartDao;
import com.marsshop.domain.Cart;
import com.marsshop.domain.Goods;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl extends BaseDao implements CartDao {
    @Override
    public boolean existsByUidAndGdId(Integer uid, Integer gdId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            conn = getConn();
            String sql = "select count(*) from scar where uID = ? and gdID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.setInt(2, gdId);

            rs = ps.executeQuery();
            rs.next();
            result = rs.getInt(1) > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return result;
    }

    @Override
    public void save(Cart cart) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConn();
            String sql = "insert into scar(uID, gdID, scNum) values (?, ? , ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cart.getUid());
            ps.setInt(2, cart.getGoods().getGdId());
            ps.setInt(3, cart.getScNum());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
    }

    @Override
    public List<Cart> selectByUid(Integer uid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cart cart = null;
        Goods goods = null;
        List<Cart> cartList = new ArrayList<>();
        try {
            conn = getConn();
            String sql = "select s.*, g.gdImage, g.gdPrice, g.gdName " +
                    "from scar s, good g " +
                    "where g.gdID = s.gdID and s.uID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                cart = new Cart();
                cart.setScId(rs.getInt("scID"));
                cart.setUid(uid);
                cart.setScNum(rs.getInt("scNum"));
                goods = new Goods();
                goods.setGdId(rs.getInt("gdID"));
                goods.setGdImage(rs.getString("gdImage"));
                goods.setGdPrice(rs.getBigDecimal("gdPrice"));
                goods.setGdName(rs.getString("gdName"));
                cart.setGoods(goods);
                cartList.add(cart);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return cartList;
    }

    @Override
    public void updateScNum(Integer scId, Integer scNum) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "update scar set scNum = ? where scID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, scNum);
            ps.setInt(2, scId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
    }

    @Override
    public void delete(Integer scId) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "delete from scar where scID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, scId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
    }
}
