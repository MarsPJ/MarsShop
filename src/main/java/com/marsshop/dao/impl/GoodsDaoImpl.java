package com.marsshop.dao.impl;

import com.marsshop.dao.BaseDao;
import com.marsshop.dao.GoodsDao;
import com.marsshop.domain.Goods;
import com.marsshop.domain.Goodstype;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl extends BaseDao implements GoodsDao {

    @Override
    public boolean existsByTid(Integer[] tids) {

        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        boolean result = false;
        try {
            conn = getConn();
            StringBuilder sqls = new StringBuilder("select count(*) from good where tID in (");
            for (Integer tid : tids) {
                sqls.append("?,");
            }
            ps = conn.prepareStatement(sqls.substring(0, sqls.length() - 1) + ")");
            for (int i = 0; i < tids.length; i++) {
                ps.setInt(i + 1, tids[i]);
            }
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
    public int selectCountByParam(Integer tid, String gdName) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int count = 0;
        try {
            conn = getConn();
            String sql = "select count(*) from good where 1 = 1";
            List<Object> params = new ArrayList<>();
            if (tid != null) {
                sql += " and tId = ?";
                params.add(tid);
            }
            if (gdName != null && !gdName.isEmpty()) {
                sql += " and gdName like ?";
                params.add("%" + gdName + "%");
            }
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
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
    public List<Goods> selectByParam(Integer tid, String gdName, int firstIndex, int pageSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Goods> goodsList = new ArrayList<>();
        Goods goods = null;

        try {
            conn = getConn();
            String sql = "select gdID, g.tID, gdCode, gdName, gdPrice, gdQuantity, gdSaleQty, \n" +
                    "                  gdCity, gdImage, gdInfo, gdAddTime, gdHot, t.tName from good g, goodtype t where g.tID = t.tID";

            List<Object> params = new ArrayList<>();
            if (tid != null) {
                sql += " and g.tId = ?";
                params.add(tid);
            }
            if (gdName != null && !gdName.isEmpty()) {
                sql += " and gdName like ?";
                params.add("%" + gdName + "%");
            }
            sql += " order by gdAddTime desc LIMIT ?, ?";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            ps.setInt(params.size() + 1, firstIndex);
            ps.setInt(params.size() + 2, pageSize);
            rs = ps.executeQuery();
            int count = 0;
            System.out.println(count);
            while (rs.next()) {
                count++;
                System.out.println(count);
                goods = new Goods();
                goods.setGdAddTime(rs.getTimestamp("gdAddTime"));
                goods.setGdCity(rs.getString("gdCity"));
                goods.setGdCode(rs.getString("gdCode"));
                goods.setGdHot(rs.getInt("gdHot"));
                goods.setGdId(rs.getInt("gdId"));
                goods.setGdImage(rs.getString("gdImage"));
                goods.setGdInfo(rs.getString("gdInfo"));
                goods.setGdPrice(rs.getBigDecimal("gdPrice"));
                goods.setGdQuantity(rs.getInt("gdQuantity"));
                goods.setGdSaleQty(rs.getInt("gdSaleQty"));
                goods.setGdName(rs.getString("gdName"));
                Goodstype goodstype = new Goodstype();
                goodstype.setTid(rs.getInt("tid"));
                goodstype.setTname(rs.getString("tname"));
                goods.setType(goodstype);
                goodsList.add(goods);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return goodsList;
    }

    @Override
    public boolean existsByGdCode(Integer gdId, String gdCode) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            conn = getConn();
            String sql = "select count(*) from good where gdCode = ?";
            if (gdId != null) {
                sql += " and gdID != ?";
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, gdCode);
            if (gdId != null) {
                ps.setInt(2, gdId);
            }
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
    public boolean existsByGdName(Integer gdId, String gdName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            conn = getConn();
            String sql = "select count(*) from good where gdName = ?";
            if (gdId != null) {
                sql += " and gdID != ?";
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, gdName);
            if (gdId != null) {
                ps.setInt(2, gdId);
            }
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
    public void save(Goods goods) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "insert into good (tID, gdCode, gdName, gdPrice, gdQuantity, gdSaleQty,\n" +
                    "gdCity, gdImage, gdInfo, gdAddTime, gdHot)\n" +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, goods.getType().getTid());
            ps.setString(2, goods.getGdCode());
            ps.setString(3, goods.getGdName());
            ps.setBigDecimal(4, goods.getGdPrice());
            ps.setInt(5, goods.getGdQuantity());
            ps.setInt(6, goods.getGdSaleQty());
            ps.setString(7, goods.getGdCity());
            ps.setString(8, goods.getGdImage());
            ps.setString(9, goods.getGdInfo());

            ps.setTimestamp(10, new Timestamp(goods.getGdAddTime().getTime()));
            ps.setInt(11, goods.getGdHot());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }

    }

    @Override
    public void delete(Integer[] gdIds) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "delete from good where gdID in (";
            for (Integer gdId : gdIds) {
                sql += "?,";
            }
            sql = sql.substring(0, sql.length() - 1) + ")";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < gdIds.length; i++) {
                ps.setInt(i + 1, gdIds[i]);
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }

    }

    @Override
    public Goods selectByGdId(Integer gdId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Goods goods = null;
        try {
            conn = getConn();
            String sql = "select gdID, g.tID, gdCode, gdName, gdPrice, gdQuantity, gdSaleQty, \n" +
                    "                  gdCity, gdImage, gdInfo, gdAddTime, gdHot, t.tName from good g, goodtype t " +
                    "                  where g.tID = t.tID and gdID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, gdId);
            rs = ps.executeQuery();
            rs.next();
            goods = new Goods();
            goods.setGdAddTime(rs.getTimestamp("gdAddTime"));
            goods.setGdCity(rs.getString("gdCity"));
            goods.setGdCode(rs.getString("gdCode"));
            goods.setGdHot(rs.getInt("gdHot"));
            goods.setGdId(rs.getInt("gdId"));
            goods.setGdImage(rs.getString("gdImage"));
            goods.setGdInfo(rs.getString("gdInfo"));
            goods.setGdPrice(rs.getBigDecimal("gdPrice"));
            goods.setGdQuantity(rs.getInt("gdQuantity"));
            goods.setGdSaleQty(rs.getInt("gdSaleQty"));
            goods.setGdName(rs.getString("gdName"));
            Goodstype goodstype = new Goodstype();
            goodstype.setTid(rs.getInt("tid"));
            goodstype.setTname(rs.getString("tname"));
            goods.setType(goodstype);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return goods;
    }

    @Override
    public void update(Goods goods) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            StringBuilder sql = new StringBuilder("update good set gdName = ?, tid = ?, gdCode = ?, " +
                    "gdPrice = ?, gdQuantity = ?, gdCity = ?, gdInfo = ?");

            if (goods.getGdImage() != null && !goods.getGdImage().isEmpty()) {
                sql.append(", gdImage = ?");
            }
            sql.append("where gdId = ?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, goods.getGdName());
            ps.setInt(2, goods.getType().getTid());
            ps.setString(3, goods.getGdCode());
            ps.setBigDecimal(4, goods.getGdPrice());
            ps.setInt(5, goods.getGdQuantity());
            ps.setString(6, goods.getGdCity());
            ps.setString(7, goods.getGdInfo());
            int index = 8;
            if (goods.getGdImage() != null && !goods.getGdImage().isEmpty()) {
                ps.setString(index++, goods.getGdImage());
            }
            ps.setInt(index, goods.getGdId());
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
    }
}
