package com.marsshop.dao.impl;

import com.marsshop.dao.BaseDao;
import com.marsshop.dao.GoodsHistoryDao;
import com.marsshop.domain.Goods;
import com.marsshop.domain.Goodstype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsHistoryDaoImpl extends BaseDao implements GoodsHistoryDao {
    @Override
    public List<Goods> selectByOid(Integer oid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Goods goods = null;
        List<Goods> goodsList = new ArrayList<>();
        try {
            conn = getConn();
            String sql = "select gdID, tname, gdCode, " +
                    "gdName, gdPrice, gdQuantity, gdSaleQty," +
                    " gdCity, gdInfo, gdAddTime,\n" +
                    "gdEvNum, remark, gdTotal, oid from goodshistory where oid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);
            rs = ps.executeQuery();
            while (rs.next()) {
                goods = new Goods();

                goods.setGdId(rs.getInt("gdId"));

                Goodstype type = new Goodstype();
                type.setTname(rs.getString("tname"));
                goods.setType(type);

                goods.setGdCode(rs.getString("gdCode"));
                goods.setGdName(rs.getString("gdName"));
                goods.setGdPrice(rs.getBigDecimal("gdPrice"));
                goods.setGdCity(rs.getString("gdCity"));
                goods.setGdInfo(rs.getString("gdInfo"));
                goods.setGdEvNum(rs.getInt("gdEvNum"));
                goods.setGdTotal(rs.getBigDecimal("gdTotal"));
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
    public void saveBatch(List<Goods> goodsList, Integer oid) {
        // 订单号唯一，所以一次插入goodshistory的所有信息都属于一个订单
        Connection conn = null;
        PreparedStatement ps = null;


        try {
            conn = getConn();
            String sql = "insert into goodshistory (tname, gdCode, gdName, gdPrice, " +
                    "gdCity, gdInfo, gdAddTime, " +
                    "gdEvNum, gdTotal, oid) " +
                    "select tname, gdCode, gdName, gdPrice, gdCity, gdInfo, gdAddTime, ?, ?, ? from " + // 筛选出要传入goodshistory的数据
                    "(select t.tname, g.* from good g, goodtype t where g.tID = t.tID and gdID = ?) as ss"; // 获得类型名

            ps = conn.prepareStatement(sql);

            for (Goods goods : goodsList) {
                ps.setInt(1, goods.getGdEvNum());
                ps.setBigDecimal(2, goods.getGdTotal());
                ps.setInt(3, oid);
                ps.setInt(4, goods.getGdId());
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
    }
}
