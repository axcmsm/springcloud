package com.axcmsm.mq;

import com.alibaba.fastjson.JSON;
import com.axcmsm.EsPojo.AxcmsmTest2;
import com.axcmsm.constants.MqConstants;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: com.axcmsm.mq.AxcmsmListener
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/27
 */
@Component
public class AxcmsmListener {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private List<AxcmsmTest2> arr;

    /**
     * 初始化数据
     */
    @PostConstruct
    public void init() {
        arr = new ArrayList<AxcmsmTest2>();
        arr.add(new AxcmsmTest2("6axcmsm1@qq.com", "6", "品牌飞升，啶虫脒是", "须贺", "蛮荒"));
        arr.add(new AxcmsmTest2("7axcmsm2@qq.com", "7", "几时了，我问你，几时了?", "须贺", "荒版"));
        arr.add(new AxcmsmTest2("8axcmsm3@qq.com", "8", "沉淀，无网易", "须贺", "蛮荒"));
        arr.add(new AxcmsmTest2("9xcmsm4@qq.com", "9", "入世之人，寻无本心", "圣僧", "天慈"));
        arr.add(new AxcmsmTest2("10xcmsm5@qq.com", "10", "落叶，只是为了归根把", "剑豪", "天慈"));
        arr.add(new AxcmsmTest2("11axcmsm6@qq.com", "11", "吾虽浪迹天涯，却未成迷失本心", "剑豪", "天劫"));
    }

    /**
     * 新增
     *
     * @param id
     * @throws IOException
     */
    @RabbitListener(queues = MqConstants.AXCMSM_INSERT_QUEUE)
    public void add(Long id) throws IOException {
        AxcmsmTest2 item = getById(id);
        if (item!=null){
            IndexRequest indexRequest = new IndexRequest("axcmsm_test2");
            indexRequest.source(JSON.toJSONString(item), XContentType.JSON);
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        }
    }

    /**
     * 根据id获取数据
     * @return
     */
    private AxcmsmTest2 getById(Long id){
        AxcmsmTest2 item = null;
        for (AxcmsmTest2 iter : arr) {
            if (iter.getId().equals(id.toString())) {
                item=iter;
            }
        }
        return item;
    }

    /**
     * 删除
     *
     * @param id
     * @throws IOException
     */
    @RabbitListener(queues = MqConstants.AXCMSM_DELETE_QUEUE)
    public void del(Long id) throws IOException {
        DeleteRequest indexRequest = new DeleteRequest("axcmsm_test2").id(id.toString());
        restHighLevelClient.delete(indexRequest, RequestOptions.DEFAULT);
    }
}
