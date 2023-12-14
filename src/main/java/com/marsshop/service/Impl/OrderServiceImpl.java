package com.marsshop.service.Impl;

import com.marsshop.dao.CartDao;
import com.marsshop.dao.GoodsDao;
import com.marsshop.dao.GoodsHistoryDao;
import com.marsshop.dao.OrderDao;
import com.marsshop.dao.impl.CartDaoImpl;
import com.marsshop.dao.impl.GoodsDaoImpl;
import com.marsshop.dao.impl.GoodsHistoryDaoImpl;
import com.marsshop.dao.impl.OrderDaoImpl;
import com.marsshop.domain.*;
import com.marsshop.service.OrderService;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private GoodsHistoryDao goodsHistoryDao = new GoodsHistoryDaoImpl();
    private GoodsDao goodsDao = new GoodsDaoImpl();
    private CartDao cartDao = new CartDaoImpl();
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

    @Override
    public void add(List<Goods> goodsList, Integer uid) {
        // 计算总金额
        BigDecimal total = new BigDecimal(0);
        for (Goods goods : goodsList) {
            // 设置每件商品的小计
            goods.setGdTotal(goods.getGdPrice().multiply(new BigDecimal(goods.getGdEvNum())));
            total = total.add(goods.getGdTotal());
        }
        // 生成订单编号
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String ocode = sdf.format(new Date());
        Random r = new Random();
        Integer ram = r.nextInt(10000000);
        //前面补零
        DecimalFormat df = new DecimalFormat("0000000");
        ocode += df.format(ram);
        // 生成订单
        Order order = new Order();
        order.setOcode(ocode);
        User user = new User();
        user.setUid(uid);
        order.setUser(user);
        order.setOstatus(1);
        order.setOtime(new Date());
        order.setOtotal(total);
        orderDao.save(order);
        // 保存明细
        goodsHistoryDao.saveBatch(goodsList, order.getOid());
        // 修改商品表的库存信息
        goodsDao.updateQuantityBatch(goodsList);
        // 清空购物车
        cartDao.deleteByUid(uid);
    }

    @Override
    public List<Order> selectByUid(Integer uid) {
        List<Order> orderList = orderDao.selectByUid(uid);
        return orderList;
    }
}
