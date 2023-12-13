package com.marsshop.dao;

import com.marsshop.domain.EmailSet;

/**
 * 设置发送邮箱Dao
 */
public interface EmailSetDao {
    /**
     * 添加邮箱设置
     * @param emailSet
     */
    void save(EmailSet emailSet);

    /**
     * 修改邮箱设置
     * @param emailSet
     */
    void update(EmailSet emailSet);

    /**
     * 查询设置的发送邮箱信息
     * @return
     */
    EmailSet select();
}
