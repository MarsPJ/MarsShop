package com.marsshop.domain;

import java.io.Serializable;

/**
 * 邮箱设置实体
 */
public class EmailSet implements Serializable {
    private Integer emId; // id
    private  String emServer; //发送服务器

    private  String emAddress; //与发送服务器对应的邮箱账号
    private String emPassCode ;//授权码

    public Integer getEmId() {
        return emId;
    }

    public void setEmId(Integer emId) {
        this.emId = emId;
    }

    public String getEmServer() {
        return emServer;
    }

    public void setEmServer(String emServer) {
        this.emServer = emServer;
    }

    public String getEmAddress() {
        return emAddress;
    }

    public void setEmAddress(String emAddress) {
        this.emAddress = emAddress;
    }

    public String getEmPassCode() {
        return emPassCode;
    }

    public void setEmPassCode(String emPassCode) {
        this.emPassCode = emPassCode;
    }
}
