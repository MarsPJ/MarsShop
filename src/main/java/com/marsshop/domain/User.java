package com.marsshop.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员实体类
 */
public class User implements Serializable {
    private Integer uid; //id
    private String uname ;//姓名
    private String upwd; //密码
    private String usex; //性别
    private Date ubirth; //生日
    private String uphone; //手机号
    private String uemail; //邮箱
    private String uqq; //qq
    private String uimage ; //头像
    private Integer ucredit; // 会员积分
    private Date uregTime ; //注册时间

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public Date getUbirth() {
        return ubirth;
    }

    public void setUbirth(Date ubirth) {
        this.ubirth = ubirth;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUqq() {
        return uqq;
    }

    public void setUqq(String uqq) {
        this.uqq = uqq;
    }

    public String getUimage() {
        return uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public Integer getUcredit() {
        return ucredit;
    }

    public void setUcredit(Integer ucredit) {
        this.ucredit = ucredit;
    }

    public Date getUregTime() {
        return uregTime;
    }

    public void setUregTime(Date uregTime) {
        this.uregTime = uregTime;
    }
}
