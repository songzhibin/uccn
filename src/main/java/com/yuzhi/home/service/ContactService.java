package com.yuzhi.home.service;

import com.yuzhi.home.model.Contact;
import com.yuzhi.home.model.ContactExample;

import java.util.List;

/**
 * 联系方式
 * @author soso
 * @date 2018-8-29 16:29:50
 */
public interface ContactService {
    /**
     * 保存联系方式
     * @param record
     * @return
     */
    int saveSelective(Contact record);

    /**
     * 按条件查询
     * @param example 条件
     * @return
     */
    List<Contact> selectByExample(ContactExample example);
}
