package com.marsshop.service;

import com.marsshop.domain.Goods;
import com.marsshop.domain.Order;
import com.marsshop.domain.OrderSelectParam;
import com.marsshop.domain.Page;

import java.util.List;

/**
 * 订单查询业务层接口
 */
public interface OrderService {
    /**
     * 根据条件分页查询订单
     * @param param
     * @param page
     * @return
     */
    List<Order> selectByParam(OrderSelectParam param, Page page);

    /**
     * 根据订单id更新订单状态
     * @param ostatus
     * @param oid
     */
    void updateStatus(Integer ostatus, Integer oid);

    /**
     * 根据订单id查询订单明细
     * @param oid
     * @return
     */
    List<Goods> selectDetailByOid(Integer oid);
}
