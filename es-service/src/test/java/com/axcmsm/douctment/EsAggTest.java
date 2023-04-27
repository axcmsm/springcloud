package com.axcmsm.douctment;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

/**
 * ClassName: com.axcmsm.douctment.EsAggTest
 * 微信公众号：代码飞快
 * Description:
 * 聚合搜索
 *
 * @author 须贺
 * @version 2023/4/26
 */
@SpringBootTest
public class EsAggTest {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 桶聚合
     * @throws IOException
     */
    @Test
    void BulkTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest("axcmsm_test");
        //查询条件
        searchRequest.source().query(QueryBuilders.matchAllQuery());
        searchRequest.source().size(0);// 只显示聚合结果
        //聚合查询
        searchRequest.source().aggregation(
                AggregationBuilders
                        .terms("type_agg")//别名
                        .field("type")//聚合字段
                        .size(20)//期望获取条数
        );
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        Aggregations aggregations = search.getAggregations();
        //根据名称获取聚合结果
        Terms typeAgg = aggregations.get("type_agg");
        //获取通
        List<? extends Terms.Bucket> buckets = typeAgg.getBuckets();
        //打印
        for (Terms.Bucket bucket : buckets) {
            String jsonStr = bucket.getKeyAsString();
            System.out.println(jsonStr);
        }

    }

}
