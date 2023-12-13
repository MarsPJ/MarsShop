package com.marsshop.domain;

import java.io.Serializable;

/**
 * 购物车实体类
 */
public class Cart implements Serializable {
    private Integer scId;   // 主键
    private Integer uid;    // 会员id
    private Goods goods;   // 商品
    private Integer scNum;  // 数量

    public Integer getScId() {
        return scId;
    }

    public void setScId(Integer scId) {
        this.scId = scId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getScNum() {
        return scNum;
    }

    public void setScNum(Integer scNum) {
        this.scNum = scNum;
    }
}
