package com.yuzhi.home.service.impl;

import com.yuzhi.home.dao.ContactMapper;
import com.yuzhi.home.model.Contact;
import com.yuzhi.home.model.ContactExample;
import com.yuzhi.home.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 联系方式
 * @author YI
 * @date 2018-8-29 16:29:50
 */
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactMapper contactMapper;

    @Override
    public int saveSelective(Contact record) {
        return contactMapper.insertSelective(record);
    }

    @Override
    public List<Contact> selectByExample(ContactExample example) {
        return contactMapper.selectByExample(example);
    }
}
