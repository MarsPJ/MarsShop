package com.marsshop.dao;

import com.marsshop.domain.Order;
import com.marsshop.domain.OrderSelectParam;

import java.util.List;

public interface OrderDao {
    /***
     * 查询符合条件的订单总数
     * @param param
     * @return
     */
    int selectCountByParam(OrderSelectParam param);

    /**
     * 查询符合条件的订单
     * @param param
     * @return
     */
    List<Order> selectByParam(OrderSelectParam param, Integer pageIndex, Integer pageSize);

    /**
     * 根据订单id以及新的状态更新订单状态
     * @param ostatus
     * @param oid
     */
    void updateStatus(Integer ostatus, Integer oid);
}
