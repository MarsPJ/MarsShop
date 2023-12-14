package com.marsshop.dao;

import com.marsshop.domain.Cart;

import java.util.List;

/**
 * 购物车Dao
 */
public interface CartDao {
    /**
     * 根据uid和gdId查询购物车
     * @param uid
     * @param gdId
     * @return
     */
    boolean existsByUidAndGdId(Integer uid, Integer gdId);

    /**
     * 保存购物车信息
     * @param cart
     */
    void save(Cart cart);

    /**
     * 根据会员id查询购物车信息
     * @param uid
     * @return
     */
    List<Cart> selectByUid(Integer uid);

    /**
     * 根据购物车id和购物车选择商品数量更新scNum
     * @param scId
     * @param scNum
     */
    void updateScNum(Integer scId, Integer scNum);

    /**
     * 根据购物车编号删除购物车
     * @param scId
     */
    void delete(Integer scId);

    /**
     * 根据用户id删除购物车
     * @param uid
     */
    void deleteByUid(Integer uid);

}
