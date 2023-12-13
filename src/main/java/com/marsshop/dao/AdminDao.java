package com.marsshop.dao;

import com.marsshop.domain.Admin;

import java.util.Date;

/**
 * 后台用户数据访问层接口
 */
public interface AdminDao {
    /**
     * 根据用户名获得用户信息
     * @param aname
     * @return
     */
    public Admin selectByName(String aname);

    /**
     * 根据id修改最后登陆时间
     * @param aid
     * @param aLastLogin
     * @return
     */
    public void updateLastLogin(Integer aid, Date aLastLogin);


}
