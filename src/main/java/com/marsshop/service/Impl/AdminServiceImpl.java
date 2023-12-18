package com.marsshop.service.Impl;

import com.marsshop.dao.impl.AdminDaoImpl;
import com.marsshop.domain.Admin;
import com.marsshop.service.AdminService;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 后台业务实现类，使用用户名通过用户访问层获取用户信息
 */
public class AdminServiceImpl implements AdminService {
    // admin数据访问层对象
    private AdminDaoImpl adminDao = new AdminDaoImpl();
    @Override
    public Admin selectByNameAndPwd(String aname, String aPwd) {
        // 根据用户名查询对象
        Admin admin = adminDao.selectByName(aname);
        // 如果用户存在，对输入的密码加密，并与数据库密码匹配
        if (admin != null ) {
            // TODO:考虑使用更安全的加密算法，例如bcrypt。
            // 使用MD5加密
            try {
                //创建md5加密工具对象
                MessageDigest messageDigest = MessageDigest.getInstance("md5");
                // 加密
                byte[] bytes = messageDigest.digest(aPwd.getBytes(StandardCharsets.UTF_8));

                // 将加密后的字节数组转为32位的16进制字符串
                aPwd = new BigInteger(1, bytes).toString(16);
                // 匹配密码，成功返回admin
                System.out.println(aPwd);
                if (aPwd.equals(admin.getApwd())) {
                    return admin;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        // 否则返回null
        return null;
    }
}
