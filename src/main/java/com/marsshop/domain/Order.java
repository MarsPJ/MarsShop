package com.marsshop.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
    private Integer oid; //主键
    private User user; //下单会员
    private String ocode; //订单编号
    private Date otime; //下单时间
    private BigDecimal ototal ; //订单总金额
    private  Integer ostatus ; //订单状态，1已下单，2已发货，3已确认收货

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOcode() {
        return ocode;
    }

    public void setOcode(String ocode) {
        this.ocode = ocode;
    }

    public Date getOtime() {
        return otime;
    }

    public void setOtime(Date otime) {
        this.otime = otime;
    }

    public BigDecimal getOtotal() {
        return ototal;
    }

    public void setOtotal(BigDecimal ototal) {
        this.ototal = ototal;
    }

    public Integer getOstatus() {
        return ostatus;
    }

    public void setOstatus(Integer ostatus) {
        this.ostatus = ostatus;
    }
}
