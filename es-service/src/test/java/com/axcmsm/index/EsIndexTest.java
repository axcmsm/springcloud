package com.axcmsm.index;

import com.axcmsm.mapping.EsMappingConstant;
import com.mysql.cj.jdbc.result.UpdatableResultSet;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * ClassName: com.axcmsm.index.EsIndexTest
 * 微信公众号：代码飞快
 * Description:
 * 索引操作
 * @author 须贺
 * @version 2023/4/26
 */
@SpringBootTest
public class EsIndexTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引
     * @throws IOException
     */
    @Test
    void create_index() throws IOException {
        CreateIndexRequest indexRequest = new CreateIndexRequest("axcmsm_test");
        indexRequest.source(EsMappingConstant.AXCMSM_TEST_MAPPING, XContentType.JSON);
        restHighLevelClient.indices().create(indexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 删除操作
     * @throws IOException
     */
    @Test
    void delete_index() throws IOException {
        DeleteIndexRequest indexRequest = new DeleteIndexRequest("axcmsm_test");
        restHighLevelClient.indices().delete(indexRequest,RequestOptions.DEFAULT);
    }


    /**
     * 判断索引库是否存在
     * @throws IOException
     */
    @Test
    void exists_index() throws IOException {
        GetIndexRequest indexRequest=new GetIndexRequest("axcmsm_test");
        boolean exists = restHighLevelClient.indices().exists(indexRequest, RequestOptions.DEFAULT);
        System.err.println(exists);
    }


}
