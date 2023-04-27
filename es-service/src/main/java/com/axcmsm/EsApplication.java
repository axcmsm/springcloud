package com.axcmsm;

import com.axcmsm.mapper.NovelRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * ClassName: com.axcmsm.EsApplication
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/26
 */
@SpringBootApplication
//@EnableElasticsearchRepositories(basePackageClasses = {NovelRepository.class})
public class EsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class,args);
    }
}
