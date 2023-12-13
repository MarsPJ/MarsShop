package com.marsshop.dao.impl;

import com.marsshop.dao.BaseDao;
import com.marsshop.dao.GoodstypeDao;
import com.marsshop.domain.Goodstype;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodstypeDaoImpl extends BaseDao implements GoodstypeDao {

    @Override
    public int selectCount() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int count = 0;

        try {
            conn = getConn();
            String sql = "select COUNT(*) from goodtype";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            // 不要漏掉
            rs.next();
            // getInt(1)表示获取结果集中第一列的值，即COUNT(*)函数计算的记录总数。
            count = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return count;
    }

    @Override
    public List<Goodstype> selectByPage(int firstIndex, int pageSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Goodstype> goodstypeList = new ArrayList<>();

        Goodstype goodstype = null;
        try {
            conn = getConn();
            String sql = "select tID, tName from goodtype order by tID desc LIMIT ?, ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, firstIndex);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                goodstype = new Goodstype();
                goodstype.setTid(rs.getInt("tID"));
                goodstype.setTname(rs.getString("tName"));

                goodstypeList.add(goodstype);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }


        return goodstypeList;
    }

    @Override
    public void add(Goodstype goodstype) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "insert into goodtype (tName) values (?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, goodstype.getTname());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }


    }

    @Override
    public boolean existsByName(String tname, Integer tid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            conn = getConn();
            String sql = "select count(*) from goodtype where tName = ?";
            if (tid != null) {
                sql += " and tID != ?";
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, tname);
            if (tid != null) {
                ps.setInt(2, tid);
            }
            rs = ps.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return result;
    }

    @Override
    public void delete(Integer[] tids) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            StringBuilder sqls = new StringBuilder("delete from goodtype where tid in (");
            for (Integer tid : tids) {
                sqls.append("?,");
            }
            ps = conn.prepareStatement(sqls.substring(0, sqls.length() - 1) + ")");
            for (int i = 0; i < tids.length; i++) {
                ps.setInt(i + 1, tids[i]);
            }

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }

    }

    @Override
    public Goodstype selectById(Integer tid) {
        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;

        Goodstype goodstype = null;
        try {
            conn = getConn();
            String sql = "select tID, tName from goodtype where tID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tid);
            rs = ps.executeQuery();
            rs.next();
            goodstype = new Goodstype();
            goodstype.setTid(tid);
            goodstype.setTname(rs.getString("tName"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return goodstype;
    }

    @Override
    public void update(Goodstype goodstype) {
        Connection conn = null;
        PreparedStatement ps =null;

        try {
            conn = getConn();
            String sql = "update goodtype set tName = ? where tID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, goodstype.getTname());
            ps.setInt(2, goodstype.getTid());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
    }

    @Override
    public List<Goodstype> selectAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Goodstype> goodstypeList = new ArrayList<>();

        Goodstype goodstype = null;
        try {
            conn = getConn();
            String sql = "select tID, tName from goodtype";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                goodstype = new Goodstype();
                goodstype.setTid(rs.getInt("tID"));
                goodstype.setTname(rs.getString("tName"));

                goodstypeList.add(goodstype);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }


        return goodstypeList;
    }
}
