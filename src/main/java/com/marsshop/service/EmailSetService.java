package com.marsshop.service;

import com.marsshop.domain.EmailSet;

/**
 * 设置发送邮箱业务接口
 */
public interface EmailSetService {

    /**
     * 查询发送邮箱
     * @return
     */
    EmailSet select();

    void saveOrUpdate(EmailSet emailSet);
}
