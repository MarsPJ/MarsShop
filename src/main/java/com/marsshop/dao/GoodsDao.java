package com.marsshop.dao;

import com.marsshop.domain.Goods;
import com.marsshop.domain.Goodstype;

import java.util.List;

/**
 * 商品Dao
 */
public interface GoodsDao {
    /**
     * 根据商品的tid，即种类id，查询是否有此种类的商品
     * @param id
     * @return
     */
    boolean existsByTid(Integer[] ids);

    /**
     * 根据商品种类tid和商品名称查询商品数量
     * @param tid
     * @param gdName
     * @return
     */
    int selectCountByParam(Integer tid, String gdName);

    /**
     * 根据商品种类tid和商品名称查询指定范围的商品
     * @param tid
     * @param gdName
     * @param firstIndex
     * @param pageSize
     * @return
     */
    List<Goods> selectByParam(Integer tid, String gdName, int firstIndex, int pageSize);

    /**
     * 根据货号查询商品是否存在
     * @param gdId
     * @param gdCode
     * @return
     */
    boolean existsByGdCode(Integer gdId, String gdCode);

    /**
     * 根据商品名查询商品是否存在
     * @param gdId
     * @param gdName
     * @return
     */
    boolean existsByGdName(Integer gdId, String gdName);

    /**
     * 保存商品
     * @param goods
     */
    void save(Goods goods);

    /**
     * 根据商品id批量删除商品
     * @param gdIds
     */
    void delete(Integer[] gdIds);

    /**
     * 根据商品id查询商品信息
     * @param gdId
     * @return
     */
    Goods selectByGdId(Integer gdId);

    /**
     * 修改商品信息
     * @param goods
     */
    void update(Goods goods);

    /**
     * 批量修改商品的库存和销量
     * @param goodsList
     */
    void updateQuantityBatch(List<Goods> goodsList);
}
