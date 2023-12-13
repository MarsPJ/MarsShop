package com.marsshop.service;

import com.marsshop.domain.Page;
import com.marsshop.domain.User;

import java.util.List;

/**
 * 会员业务层接口
 */
public interface UserService {
    /**
     * 根据条件分页查询会员
     * @param param
     * @param page
     * @return
     */
    List<User> selectByParam(String param, Page page);

    /**
     * 根据id查询会员信息
     * @param uid
     * @return
     */
    User selectByUid(Integer uid);

    /**
     * 根据会员信息查询会员是否存在
     * @param user
     * @return
     */
    boolean existsByUser(User user);

    /**
     * 保存会员信息
     * @param user
     */
    void save(User user);
}
