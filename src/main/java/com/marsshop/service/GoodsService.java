package com.marsshop.service;

import com.marsshop.domain.Goods;
import com.marsshop.domain.Goodstype;
import com.marsshop.domain.Page;

import java.util.List;

/**
 * 商品管理业务接口
 */
public interface GoodsService {
    /**
     * 根据tid，商品名称以及页面对象获得当前页的商品查询结果
     * @param tid
     * @param gdName
     * @param page
     * @return
     */
    List<Goods> selectByParam(Integer tid, String gdName, Page page);

    /**
     * 添加商品，如果名称已存在，返回-1，如果货号已存在，返回-2，添加成功返回1
     * @param goods
     * @return
     */
    int add(Goods goods);

    /**
     * 根据商品id批量删除商品
     * @param gdIds
     */
    void delete(Integer[] gdIds);

    /**
     * 根据商品id查询商品
     * @param gdId
     * @return
     */
    Goods selectByGdId(Integer gdId);

    /**
     * 更新商品信息，如果名称已存在，返回-1，如果货号已存在，返回-2，添加成功返回1
     * @param goods
     * @return
     */
    int update(Goods goods);
}
