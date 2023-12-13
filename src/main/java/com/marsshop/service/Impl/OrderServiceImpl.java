package com.marsshop.service.Impl;

import com.marsshop.dao.GoodsHistoryDao;
import com.marsshop.dao.OrderDao;
import com.marsshop.dao.impl.GoodsHistoryDaoImpl;
import com.marsshop.dao.impl.OrderDaoImpl;
import com.marsshop.domain.Goods;
import com.marsshop.domain.Order;
import com.marsshop.domain.OrderSelectParam;
import com.marsshop.domain.Page;
import com.marsshop.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private GoodsHistoryDao goodsHistoryDao = new GoodsHistoryDaoImpl();
    @Override
    public List<Order> selectByParam(OrderSelectParam param, Page page) {
        int count = orderDao.selectCountByParam(param);
        page.setCount(count);

        List<Order> orderList = orderDao.selectByParam(param, page.getFirstIndex(), page.getPageSize());


        return orderList;
    }

    @Override
    public void updateStatus(Integer ostatus, Integer oid) {
        orderDao.updateStatus(ostatus, oid);
    }

    @Override
    public List<Goods> selectDetailByOid(Integer oid) {
        List<Goods> goodsList = goodsHistoryDao.selectByOid(oid);
        return goodsList;
    }
}
