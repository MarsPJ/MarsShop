package com.marsshop.domain;

import java.io.Serializable;
import java.util.Date;

public class OrderSelectParam implements Serializable {
    private  String ocode; //订单编号
    private String uname ;//会员姓名
    private Date otimeBegin ; //开始时间
    private Date otimeEnd; //结束时间
    private Integer ostatus ; //订单状态

    public String getOcode() {
        return ocode;
    }

    public void setOcode(String ocode) {
        this.ocode = ocode;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Date getOtimeBegin() {
        return otimeBegin;
    }

    public void setOtimeBegin(Date otimeBegin) {
        this.otimeBegin = otimeBegin;
    }

    public Date getOtimeEnd() {
        return otimeEnd;
    }

    public void setOtimeEnd(Date otimeEnd) {
        this.otimeEnd = otimeEnd;
    }

    public Integer getOstatus() {
        return ostatus;
    }

    public void setOstatus(Integer ostatus) {
        this.ostatus = ostatus;
    }
}
