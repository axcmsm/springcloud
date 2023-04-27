package com.axcmsm.mapper;

import com.axcmsm.pojo.CwyArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: com.axcmsm.mapper.ArticleMapper
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/26
 */
@Mapper
public interface ArticleMapper {
    @Select("select * from cwy_article")
    List<CwyArticle> findAll();
}
