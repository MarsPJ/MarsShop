package com.marsshop.service.Impl;

import com.marsshop.dao.GoodsDao;
import com.marsshop.dao.impl.GoodsDaoImpl;
import com.marsshop.domain.Goods;
import com.marsshop.domain.Page;
import com.marsshop.service.GoodsService;

import java.util.Date;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    // 商品Dao
    GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public List<Goods> selectByParam(Integer tid, String gdName, Page page) {
        // 先更新page的参数
        int count = goodsDao.selectCountByParam(tid, gdName);
        page.setCount(count);
        List<Goods> goodsList = goodsDao.selectByParam(tid, gdName, page.getFirstIndex(), page.getPageSize());
        return goodsList;
    }

    @Override
    public int add(Goods goods) {
        boolean exists = goodsDao.existsByGdName(null, goods.getGdName());
        if (exists) {
            return -1;
        }

        exists = goodsDao.existsByGdCode(null, goods.getGdCode());

        if (exists) {
            return -2;
        }

        //更新添加时间
        goods.setGdAddTime(new Date());
        goods.setGdSaleQty(0); // 销量初始化为0
        goods.setGdHot(0); // 初始化为非热销

        goodsDao.save(goods);

        return 1;
    }

    @Override
    public void delete(Integer[] gdIds) {
        goodsDao.delete(gdIds);
    }

    @Override
    public Goods selectByGdId(Integer gdId) {
        Goods goods = goodsDao.selectByGdId(gdId);
        return goods;
    }

    @Override
    public int update(Goods goods) {
        boolean exists = goodsDao.existsByGdName(goods.getGdId(), goods.getGdName());
        if (exists) {
            return -1;
        }

        exists = goodsDao.existsByGdCode(goods.getGdId(), goods.getGdCode());

        if (exists) {
            return -2;
        }

        //更新添加时间
        goods.setGdAddTime(new Date());
        goods.setGdSaleQty(0); // 销量初始化为0
        goods.setGdHot(0); // 初始化为非热销

        goodsDao.update(goods);

        return 1;
    }
}
