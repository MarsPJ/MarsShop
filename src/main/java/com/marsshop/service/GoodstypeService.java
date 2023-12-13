package com.marsshop.service;

import com.marsshop.domain.Goodstype;
import com.marsshop.domain.Page;

import java.util.List;

/**
 * 商品类别的业务接口
 */
public interface GoodstypeService {
    /**
     * 分页查询类别
     * @param page
     * @return
     */
    public List<Goodstype> selectByPage(Page page);

    /**
     * 增加商品类别，已存在添加失败返回-1，否则返回1
     * @param goodstype
     * @return
     */
    public int add(Goodstype goodstype);

    /**
     * 根据tid删除商品类别，该类别存在商品，返回-1，无法删除，否则返回1
     * @param tids
     * @return
     */
    int delete(Integer[] tids);

    /**
     * 根据tid返回商品种类对象
     * @param tid
     * @return
     */
    Goodstype selectById(Integer tid);

    /**
     * 修改商品类别信息，如果商品名已存在，修改失败，返回-1，否则返回1
     * @param goodstype
     * @return
     */
    int update(Goodstype goodstype);

    /**
     * 查询所有商品类别
     * @return
     */
    List<Goodstype> selectAll();
}
