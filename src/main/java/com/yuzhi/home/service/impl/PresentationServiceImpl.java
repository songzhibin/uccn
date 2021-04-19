package com.yuzhi.home.service.impl;

import com.yuzhi.home.dao.PresentationMapper;
import com.yuzhi.home.model.Presentation;
import com.yuzhi.home.model.PresentationExample;
import com.yuzhi.home.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 简介
 * @author soso
 * @date 2018-8-29 23:18:13
 */
@Service
public class PresentationServiceImpl implements PresentationService {
    @Autowired
    PresentationMapper presentationMapper;

    @Override
    public int save(Presentation presentation) {
        return presentationMapper.insertSelective(presentation);
    }

    @Override
    public Presentation find() {
        PresentationExample example = new PresentationExample();
        example.setLimit(1);
        example.setOrderByClause("crateDate DESC");
        PresentationExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo((byte)1);

        List<Presentation> presentations = presentationMapper.selectByExampleWithBLOBs(example);

        if (presentations != null && presentations.size() > 0){
            return presentations.get(0);
        }

        return null;
    }

    @Override
    public int update(Presentation presentation) {
        return presentationMapper.updateByPrimaryKeyWithBLOBs(presentation);
    }
}
