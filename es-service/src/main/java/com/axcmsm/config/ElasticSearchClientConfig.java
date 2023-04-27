package com.axcmsm.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * ClassName: com.axcmsm.config.ESConfig
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/26
 */
@Configuration
public class ElasticSearchClientConfig {
    //注入bean 开启
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(RestClient.builder(
                new HttpHost("192.168.64.101", 9200, "http")
//                new HttpHost("192.168.64.101", 9200, "http"),
//                new HttpHost("192.168.64.101", 9200, "http"),
        ));
    }
}
