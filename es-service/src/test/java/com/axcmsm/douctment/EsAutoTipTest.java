package com.axcmsm.douctment;

import com.alibaba.fastjson.JSON;
import com.axcmsm.EsPojo.AxcmsmTest2;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: com.axcmsm.douctment.EsAggTest
 * 微信公众号：代码飞快
 * Description:
 * 聚合搜索
 * 自动补全功能
 *
 * @author 须贺
 * @version 2023/4/26
 */
@SpringBootTest
public class EsAutoTipTest {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 模拟放入数据
     */
    @Test
    void addData() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        ArrayList<AxcmsmTest2> arr = new ArrayList<>();
        arr.add(new AxcmsmTest2("axcmsm1@qq.com", "1", "品牌飞升，啶虫脒是", "须贺", "蛮荒"));
        arr.add(new AxcmsmTest2("axcmsm2@qq.com", "2", "几时了，我问你，几时了?", "须贺", "荒版"));
        arr.add(new AxcmsmTest2("axcmsm3@qq.com", "3", "沉淀，无网易", "须贺", "蛮荒"));
        arr.add(new AxcmsmTest2("axcmsm4@qq.com", "4", "入世之人，寻无本心", "圣僧", "天慈"));
        arr.add(new AxcmsmTest2("axcmsm5@qq.com", "5", "落叶，只是为了归根把", "剑豪", "天慈"));
        arr.add(new AxcmsmTest2("axcmsm6@qq.com", "6", "吾虽浪迹天涯，却未成迷失本心", "剑豪", "天劫"));
        for (AxcmsmTest2 iter : arr) {
            bulkRequest.add(
                    new IndexRequest("axcmsm_test2").id(iter.getId()).source(JSON.toJSONString(iter), XContentType.JSON)
            );
        }
        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }


    /**
     * 自动补全
     * @throws IOException
     */
    @Test
    void AutoSearchTest() throws IOException {
        SearchRequest request=new SearchRequest("axcmsm_test2");

        request.source().suggest(
                new SuggestBuilder().addSuggestion(
                        "mySuggestion", //自定义关键字名称
                        SuggestBuilders.completionSuggestion("suggestion")
                                .prefix("h")//搜索关键字
                                .skipDuplicates(true)//去重
                                .size(10)
                )
        );

        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        //System.out.println(response);

        Suggest suggest = response.getSuggest();
        CompletionSuggestion mySuggestion = suggest.getSuggestion("mySuggestion");
        for (CompletionSuggestion.Entry.Option option : mySuggestion.getOptions()) {
            String text = option.getText().string();
            System.out.println(text);
        }
    }

}
