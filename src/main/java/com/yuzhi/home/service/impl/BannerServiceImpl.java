package com.yuzhi.home.service.impl;

import com.yuzhi.home.dao.BannerMapper;
import com.yuzhi.home.model.Banner;
import com.yuzhi.home.model.BannerExample;
import com.yuzhi.home.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 网站首页
 * @author YI
 * @date 2018-8-29 14:36:27
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerMapper bannerMapper;

    @Override
    public int save(Banner record) {
        return bannerMapper.insert(record);
    }

    @Override
    public int saveSelective(Banner record) {
        return bannerMapper.insertSelective(record);
    }

    @Override
    public List<Banner> selectByExampleWithBLOBs(BannerExample example) {
        return bannerMapper.selectByExampleWithBLOBs(example);
    }
}
