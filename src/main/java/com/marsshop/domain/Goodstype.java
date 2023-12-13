package com.marsshop.domain;

import java.io.Serializable;

/**
 * 商品类别
 */
public class Goodstype implements Serializable {

    private  Integer tid; // 自增
    private String tname; // 类别名称

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
