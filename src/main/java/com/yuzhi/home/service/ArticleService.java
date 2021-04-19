package com.yuzhi.home.service;

import com.yuzhi.home.model.ArticleExample;
import com.yuzhi.home.model.ArticleWithBLOBs;

import java.util.List;

/**
 * 文章操作
 * @author soso
 * @date 2018-8-30 11:47:38
 */
public interface ArticleService {

    /**
     * 保存文章
     * @param article
     * @return
     */
    int save(ArticleWithBLOBs article);

    /**
     * 根据id查询文章详情
     * @param id
     * @return
     */
    ArticleWithBLOBs findArticle(Integer id);

    List<ArticleWithBLOBs> findHomeArticle(ArticleExample  example);
}
