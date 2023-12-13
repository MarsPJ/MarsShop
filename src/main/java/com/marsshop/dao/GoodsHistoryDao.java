package com.marsshop.dao;

import com.marsshop.domain.Goods;

import java.util.List;

/**
 * 订单明细Dao
 */
public interface GoodsHistoryDao {
    /**
     * 根据订单id查询订单商品
     * @param oid
     * @return
     */
    List<Goods> selectByOid(Integer oid);
}
