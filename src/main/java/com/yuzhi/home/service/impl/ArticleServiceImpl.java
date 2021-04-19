package com.yuzhi.home.service.impl;

import com.yuzhi.home.dao.ArticleMapper;
import com.yuzhi.home.model.ArticleExample;
import com.yuzhi.home.model.ArticleWithBLOBs;
import com.yuzhi.home.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章操作
 * @author YI
 * @date 2018-8-30 11:47:38
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public int save(ArticleWithBLOBs article) {
        return articleMapper.insertSelective(article);
    }

    @Override
    public ArticleWithBLOBs findArticle(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ArticleWithBLOBs> findHomeArticle(ArticleExample example) {
        return articleMapper.selectByExampleWithBLOBs(example);
    }
}
