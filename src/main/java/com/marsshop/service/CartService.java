package com.marsshop.service;

import com.marsshop.domain.Cart;

import java.util.List;

/**
 * 购物车业务层接口
 */
public interface CartService {
    /**
     * 添加购物车，已存在返回-1，添加成功返回1，并且初始化scNum为1
     * @param cart
     * @return
     */
    int add(Cart cart);

    /**
     * 根据会员id查询购物车信息
     * @param uid
     * @return
     */
    List<Cart> selectByUid(Integer uid);

    /**
     * 修改购物车中的商品数量，如果传入数量大于商品库存，返回-1，否则修改成功返回1
     * @param car
     * @return
     */
    int updateScNum(Cart cart);

    /**
     * 根据购物车id删除购物车
     * @param scId
     */
    void delete(Integer scId);
}
