package com.marsshop.service.Impl;

import com.marsshop.dao.EmailSetDao;
import com.marsshop.dao.impl.EmailSetDaoImpl;
import com.marsshop.domain.EmailSet;
import com.marsshop.service.EmailSetService;

public class EmailSetServiceImpl implements EmailSetService {
    EmailSetDao emailSetDao = new EmailSetDaoImpl();
    @Override
    public EmailSet select() {
        EmailSet emailSet = emailSetDao.select();
        return emailSet;
    }

    @Override
    public void saveOrUpdate(EmailSet emailSet) {
        if (emailSet.getEmId() != null) {
            emailSetDao.update(emailSet);
        } else {
            emailSetDao.save(emailSet);
        }
    }
}
