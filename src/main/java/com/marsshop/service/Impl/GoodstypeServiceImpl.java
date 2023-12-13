package com.marsshop.service.Impl;

import com.marsshop.dao.GoodsDao;
import com.marsshop.dao.GoodstypeDao;
import com.marsshop.dao.impl.GoodsDaoImpl;
import com.marsshop.dao.impl.GoodstypeDaoImpl;
import com.marsshop.domain.Goodstype;
import com.marsshop.domain.Page;
import com.marsshop.service.GoodstypeService;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品类别业务实现类，根据page对象返回相应的的商品类别信息
 */
public class GoodstypeServiceImpl implements GoodstypeService {
    // 商品类别DAO对象
    private GoodstypeDao goodstypeDao = new GoodstypeDaoImpl();
    // 商品Dao对象
    private GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public List<Goodstype> selectByPage(Page page) {

        // 查询总数
        int count = goodstypeDao.selectCount();
        //TODO:其实可以修改一下prevIndex等的初始地方
        page.setCount(count);

        // 分页查询记录
        List<Goodstype> goodstypeList = goodstypeDao.selectByPage(page.getFirstIndex(), page.getPageSize());
        return goodstypeList;
    }

    @Override
    public int add(Goodstype goodstype) {
        if (goodstypeDao.existsByName(goodstype.getTname(), null)) {
            return -1;
        }
        goodstypeDao.add(goodstype);
        return 1;
    }

    @Override
    public int delete(Integer[] tids) {
        if (goodsDao.existsByTid(tids)) {
            return -1;
        } else {
            goodstypeDao.delete(tids);
            return 1;
        }
    }

    @Override
    public Goodstype selectById(Integer tid) {
        Goodstype goodstype = goodstypeDao.selectById(tid);
        return goodstype;
    }

    @Override
    public int update(Goodstype goodstype) {
        boolean isExist = goodstypeDao.existsByName(goodstype.getTname(), goodstype.getTid());
        if (isExist) {
            return -1;
        } else {
            goodstypeDao.update(goodstype);
            return 1;
        }
    }

    @Override
    public List<Goodstype> selectAll() {
        List<Goodstype> goodstypeList = goodstypeDao.selectAll();
        return goodstypeList;
    }
}
