package com.axcmsm.douctment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Map;

/**
 * ClassName: com.axcmsm.douctment.EsSearchTest
 * 微信公众号：代码飞快
 * Description:
 * 查询操作
 * @author 须贺
 * @version 2023/4/26
 */
@SpringBootTest
public class EsSearchTest {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 查询全部：快速入门
     *
     * @throws IOException
     */
    @Test
    void MatchAllTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest("axcmsm_test");
        searchRequest.source().query(QueryBuilders.matchAllQuery());
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        // 封装打印结果集
        handleRes(search);
    }

    /**
     * 封装普通打印结果集
     *
     * @param search
     */
    private void handleRes(SearchResponse search) {
        SearchHits hits = search.getHits();
        long count = hits.getTotalHits().value;
        System.out.println("一共：" + count + "条");
        for (SearchHit hit : hits.getHits()) {
            String json = hit.getSourceAsString();
            System.out.println(json);
        }
    }

    /**
     * 匹配查询
     *
     * @throws IOException
     */
    @Test
    void QueryTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest("axcmsm_test");

        //单字段查询，如果配置了copy_to 可以使用单字段，代替多字段，提升性能
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("info", "命运");

        //多字段查询，如果配置了copy_to 可以使用单字段，代替多字段，提升性能
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("6", "id", "info");

        //精确查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("id", "2");

        //范围查询
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("id").gte(3).lte(6);

        //布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("id", "3"))
                .filter(QueryBuilders.rangeQuery("id").gte(3).lte(6));


        //广告权重置顶
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(
                QueryBuilders.matchQuery("name", "命运"),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                        new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                // 那个掏了钱 => 过滤
                                QueryBuilders.termQuery("name", "须贺"),
                                // 陶了多少 => 权重
                                ScoreFunctionBuilders.weightFactorFunction(5)
                        )
                }
        );


        searchRequest.source().query(
//                matchQueryBuilder
//                multiMatchQueryBuilder
//                termQueryBuilder
                rangeQueryBuilder
//                boolQueryBuilder
        );


        //分页
        int page = 1, size = 2;
        searchRequest.source().from((page - 1) * size).size(size);

        //排序
        searchRequest.source().sort("_id", SortOrder.DESC);

        //离我最近的位置
      /*  searchRequest.source().sort(
                SortBuilders.geoDistanceSort("location",new GeoPoint("21,21,131.5"))
                        .order(SortOrder.ASC)
                        .unit(DistanceUnit.KILOMETERS)
        );
        // 距离结果在：sort里面
        */


        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        // 取结果集
        handleRes(search);
    }

    /**
     * 高亮构建和解析
     *
     * @throws IOException
     */
    @Test
    void MatchHighTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest("axcmsm_test");
        searchRequest.source().query(QueryBuilders.matchQuery("info", "命运"));

        //构建高亮
        searchRequest.source().highlighter(
                new HighlightBuilder().field("info")
                        //是否需要跟查询字段匹配
                        .requireFieldMatch(false)
                        .preTags("<span style='color:red'>")
                        .postTags("</span>")
        );

        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        // 解析高亮
        SearchHits hits = search.getHits();
        long count = hits.getTotalHits().value;
        System.out.println("一共：" + count + "条");
        for (SearchHit hit : hits.getHits()) {
            String json = hit.getSourceAsString();
            //获取对象
            JSONObject object = JSON.parseObject(json);
            //获取高亮结果
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (!CollectionUtils.isEmpty(highlightFields)) {
                HighlightField field = highlightFields.get("info");
                if (field != null) {
                    object.put("info", field.getFragments()[0].toString());
                }
            }
            System.out.println(object);
        }
    }
}
