package com.axcmsm;

import com.axcmsm.mapper.ArticleMapper;
import com.axcmsm.mapper.NovelRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: com.axcmsm.Test
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/26
 */
@SpringBootTest
public class TestApplication {

    @Autowired
    public RestHighLevelClient restHighLevelClient;


    @Test
    void test1() {
        System.out.println(restHighLevelClient);
    }

    @Autowired
    NovelRepository novelRepository;

    @Test
    void test2() {
        System.out.println(novelRepository);
    }


    @Autowired
    private ArticleMapper articleMapper;


    @Test
    void selectMysql(){
        articleMapper.findAll().forEach(System.out::println);
    }


}
