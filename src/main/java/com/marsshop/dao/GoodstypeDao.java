package com.marsshop.dao;

import com.marsshop.domain.Goodstype;

import java.util.List;

public interface GoodstypeDao {
    /**
     * 获取商品类别总数
     * @return
     */
    public int selectCount();

    /**
     * 根据开始页码和一个页面显示的商品类别最大数量查询数据
     * @param firstIndex
     * @param pageSize
     * @return
     */
    public List<Goodstype> selectByPage(int firstIndex, int pageSize);

    /**
     * 添加商品类别
     * @param goodstype
     */
    public void add(Goodstype goodstype);

    /**
     * 如果tid为null,根据商品类别名称查询商品类别是否已存在，存在返回true，否则返回false
     * 如果tid不为null，则如果商品名存在但并且id不一样，返回false，否则返回true
     * @param tname
     * @return
     */
    public boolean existsByName(String tname, Integer tid);

    /**
     * 根据商品类别的id删除商品类别
     * @param goodstype
     */
    void delete(Integer[] tids);

    /**
     * 根据商品种类id，返回商品种类信息
     * @param tid
     * @return
     */
    Goodstype selectById(Integer tid);

    /**
     * 修改商品类别信息
     * @param goodstype
     */
    void update(Goodstype goodstype);

    /**
     * 查询所有商品类别
     * @return
     */
    List<Goodstype> selectAll();
}
