package com.marsshop.dao;

import com.marsshop.domain.User;

import java.util.List;

/**
 * 会员数据访问接口
 */
public interface UserDao {
    /**
     * 根据条件（会员名或者电话号码）查询当前页的会员信息
     * @param param
     * @param firstIndex
     * @param pageSize
     * @return
     */
    List<User> selectByParam(String param, int firstIndex, int pageSize);

    /**
     * 根据条件（会员名或者电话号码）查询当前页的会员数量
     * @param param
     * @return
     */
    int selectCountByParam(String param);

    /**
     * 根据会员id查询会员信息
     * @param uid
     * @return
     */
    User selectByUid(Integer uid);

    /**
     * 根据会员名查询会员信息
     * @param uname
     * @return
     */
    User selectByUname(String uname);

    /**
     * 根据会员信息查询会员是否存在
     * @param user
     * @return
     */
    boolean existByUser(User user);

    /**
     * 保存会员信息
     * @param user
     */
    void save(User user);
}
