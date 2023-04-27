package com.axcmsm.douctment;

import com.alibaba.fastjson.JSON;
import com.axcmsm.EsPojo.AxcmsmTest;
import com.axcmsm.mapping.EsMappingConstant;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * ClassName: com.axcmsm.douctment.EsDouctment
 * 微信公众号：代码飞快
 * Description:
 * 文档基本操作
 * @author 须贺
 * @version 2023/4/26
 */
@SpringBootTest
public class EsDocumentTest {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 添加数据 案例一
     */
    @Test
    void add_data() throws IOException {
        IndexRequest indexRequest = new IndexRequest("axcmsm_test").id("1")
                .source(EsMappingConstant.AXCMSM_TEST_ADD, XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index.status());
    }

    /**
     * 添加数据 案例案例二
     */
    @Test
    void add_data_object() throws IOException {
        AxcmsmTest pojo = new AxcmsmTest();
        pojo.setId("2");
        pojo.setInfo("成功的秘诀在于对目标的追求，而不是对时间的等待。");
        pojo.setName(new AxcmsmTest.Name("歌", "德"));
        System.out.println(pojo);


        IndexRequest indexRequest = new IndexRequest("axcmsm_test").id(pojo.getId())
                .source(JSON.toJSONString(pojo), XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index.status());
    }

    /**
     * 添加数据 案例案例三
     * 批量擦汗人
     */
    @Test
    void add_data_bulk() throws IOException {
        ArrayList<AxcmsmTest> list = new ArrayList<>();
        list.add(new AxcmsmTest("3","成功的秘诀在于对目标的追求，而不是对时间的等待1。歌1",new AxcmsmTest.Name("歌1", "德1")));
        list.add(new AxcmsmTest("4","成功的秘诀在于对目标的追求，而不是对时间的等待2。",new AxcmsmTest.Name("歌2", "德2")));
        list.add(new AxcmsmTest("5","成功的秘诀在于对目标的追求，而不是对时间的等待3。",new AxcmsmTest.Name("歌3", "德3")));

        BulkRequest request=new BulkRequest();
        for (AxcmsmTest item : list) {
            request.add(new IndexRequest("axcmsm_test").id(item.getId()).source(JSON.toJSONString(item),XContentType.JSON));
        }

        restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
    }

    /**
     * 查询数据
     */
    @Test
    void get_data() throws IOException {
        GetRequest indexRequest = new GetRequest("axcmsm_test").id("4");
        GetResponse response = restHighLevelClient.get(indexRequest, RequestOptions.DEFAULT);
        String json = response.getSourceAsString();
        System.out.println(json);
        AxcmsmTest axcmsmTest = JSON.parseObject(json, AxcmsmTest.class);
        System.out.println(axcmsmTest);
    }

    /**
     * 更新数据：
     * - 全量更新： 跟新增一样
     * - 局部更新数据
     */
    @Test
    void update_data() throws IOException {
        UpdateRequest indexRequest = new UpdateRequest("axcmsm_test", "2")
                .doc(
                        "info", "安静的聆听着命运的悲鸣吧！"
                );
        restHighLevelClient.update(indexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 删除文档
     * @throws IOException
     */
    @Test
    void delete_data() throws IOException {
        DeleteRequest indexRequest=new DeleteRequest("axcmsm_test","1");
        restHighLevelClient.delete(indexRequest, RequestOptions.DEFAULT);
    }
}
