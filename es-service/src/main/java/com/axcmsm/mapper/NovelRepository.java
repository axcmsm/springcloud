package com.axcmsm.mapper;

import com.axcmsm.EsPojo.Cwy_article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ClassName: com.axcmsm.mapper.NovelRepository
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/26
 */
public interface NovelRepository extends ElasticsearchRepository<Cwy_article,String>{
}
