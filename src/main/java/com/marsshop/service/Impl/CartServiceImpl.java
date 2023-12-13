package com.marsshop.service.Impl;

import com.marsshop.dao.CartDao;
import com.marsshop.dao.GoodsDao;
import com.marsshop.dao.impl.CartDaoImpl;
import com.marsshop.dao.impl.GoodsDaoImpl;
import com.marsshop.domain.Cart;
import com.marsshop.domain.Goods;
import com.marsshop.service.CartService;
import com.marsshop.service.GoodsService;

import java.util.List;

public class CartServiceImpl implements CartService {
    CartDao cartDao = new CartDaoImpl();
    GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public int add(Cart cart) {
        boolean result = cartDao.existsByUidAndGdId(cart.getUid(), cart.getGoods().getGdId());
        // 不存在
        if (!result) {
            cart.setScNum(1);
            cartDao.save(cart);

            return 1;
        }
        // 已存在
        return -1;
    }

    @Override
    public List<Cart> selectByUid(Integer uid) {
        List<Cart> cartList = cartDao.selectByUid(uid);
        return cartList;
    }

    @Override
    public int updateScNum(Cart cart) {
        Goods goods = goodsDao.selectByGdId(cart.getGoods().getGdId());
        if (cart.getScNum() > goods.getGdQuantity()) {
            return -1;
        }
        cartDao.updateScNum(cart.getScId(), cart.getScNum());
        return 1;
    }

    @Override
    public void delete(Integer scId) {
        cartDao.delete(scId);
    }
}
