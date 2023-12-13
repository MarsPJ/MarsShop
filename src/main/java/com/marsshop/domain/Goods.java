package com.marsshop.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品实体类
 */
public class Goods implements Serializable {
    private Integer gdId; //id
    private Goodstype type;  //所属类型
    private String gdCode; //商品编码

    private String gdName; //商品名称
    private BigDecimal gdPrice ;//单价，浮点数
    private Integer gdQuantity; //库存
    private Integer gdSaleQty; //销量
    private String gdCity; //发送城市
    private String gdImage; //商品图片路径
    private String gdInfo ;//商品描述
    private Date gdAddTime; //商品添加时间
    private Integer gdHot ; //1表示热销，0表示非热销

    private Integer gdEvNum; // 数量
    private BigDecimal gdTotal; // 总价

    public Integer getGdId() {
        return gdId;
    }

    public void setGdId(Integer gdId) {
        this.gdId = gdId;
    }

    public Goodstype getType() {
        return type;
    }

    public void setType(Goodstype type) {
        this.type = type;
    }

    public String getGdCode() {
        return gdCode;
    }

    public void setGdCode(String gdCode) {
        this.gdCode = gdCode;
    }

    public String getGdName() {
        return gdName;
    }

    public void setGdName(String gdName) {
        this.gdName = gdName;
    }

    public BigDecimal getGdPrice() {
        return gdPrice;
    }

    public void setGdPrice(BigDecimal gdPrice) {
        this.gdPrice = gdPrice;
    }

    public Integer getGdQuantity() {
        return gdQuantity;
    }

    public void setGdQuantity(Integer gdQuantity) {
        this.gdQuantity = gdQuantity;
    }

    public Integer getGdSaleQty() {
        return gdSaleQty;
    }

    public void setGdSaleQty(Integer gdSaleQty) {
        this.gdSaleQty = gdSaleQty;
    }

    public String getGdCity() {
        return gdCity;
    }

    public void setGdCity(String gdCity) {
        this.gdCity = gdCity;
    }

    public String getGdImage() {
        return gdImage;
    }

    public void setGdImage(String gdImage) {
        this.gdImage = gdImage;
    }

    public String getGdInfo() {
        return gdInfo;
    }

    public void setGdInfo(String gdInfo) {
        this.gdInfo = gdInfo;
    }

    public Date getGdAddTime() {
        return gdAddTime;
    }

    public void setGdAddTime(Date gdAddTime) {
        this.gdAddTime = gdAddTime;
    }

    public Integer getGdHot() {
        return gdHot;
    }

    public void setGdHot(Integer gdHot) {
        this.gdHot = gdHot;
    }

    public Integer getGdEvNum() {
        return gdEvNum;
    }

    public void setGdEvNum(Integer gdEvNum) {
        this.gdEvNum = gdEvNum;
    }

    public BigDecimal getGdTotal() {
        return gdTotal;
    }

    public void setGdTotal(BigDecimal gdTotal) {
        this.gdTotal = gdTotal;
    }
}
