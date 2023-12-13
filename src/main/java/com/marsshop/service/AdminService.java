package com.marsshop.service;

import com.marsshop.domain.Admin;

/**
 * 后台用户业务接口
 */
public interface AdminService {
    /**
     * 用户登录，成功返回admin对象，否则返回null
     * @param aname
     * @param aPwd
     * @return
     */
    Admin selectByNameAndPwd(String aname, String aPwd);
}
