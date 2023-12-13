package com.marsshop.domain;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable {
    private Integer aid; // 自增id
    private String aname; // admin姓名
    private String apwd; // admin密码
    private Date aLastLogin; // 最后访问时间

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getApwd() {
        return apwd;
    }

    public void setApwd(String apwd) {
        this.apwd = apwd;
    }

    public Date getaLastLogin() {
        return aLastLogin;
    }

    public void setaLastLogin(Date aLastLogin) {
        this.aLastLogin = aLastLogin;
    }
}
