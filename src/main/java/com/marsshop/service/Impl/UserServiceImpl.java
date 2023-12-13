package com.marsshop.service.Impl;

import com.marsshop.dao.UserDao;
import com.marsshop.dao.impl.UserDaoImpl;
import com.marsshop.domain.Page;
import com.marsshop.domain.User;
import com.marsshop.service.UserService;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> selectByParam(String param, Page page) {
        int count = userDao.selectCountByParam(param);
        page.setCount(count);

        List<User> userList = userDao.selectByParam(param, page.getFirstIndex(), page.getPageSize());
        return userList;
    }

    @Override
    public User selectByUid(Integer uid) {
        User user = userDao.selectByUid(uid);
        return user;
    }

    @Override
    public boolean existsByUser(User user) {
        boolean result = userDao.existByUser(user);
        return result;
    }

    @Override
    public void save(User user) {
        // md5给密码加密
        // TODO:使用更安全的加密方式
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] bytes = messageDigest.digest(user.getUpwd().getBytes("utf-8"));
            // 将密文转成32位16进制
            String newPwd = new BigInteger(1, bytes).toString(16);
            user.setUpwd(newPwd);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 设置注册时间
        user.setUregTime(new Date());

        userDao.save(user);
    }
}
