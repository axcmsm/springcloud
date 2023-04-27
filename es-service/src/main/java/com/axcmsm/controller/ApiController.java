package com.axcmsm.controller;

import com.axcmsm.constants.MqConstants;
import org.apache.ibatis.annotations.Delete;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: com.axcmsm.controller.ApiController
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/27
 */
@RestController
public class ApiController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 添加发送消息
     * @return
     */
    @PostMapping("/add/{id}")
    public String add(@PathVariable("id") Long id){
        rabbitTemplate.convertAndSend(MqConstants.AXCMSM_EXCHANGE_TOPIC,MqConstants.AXCMSM_INSERT_KEY,id);
        return "ok";
    }

    /**
     * 删除
     * @return
     */
    @DeleteMapping("/del/{id}")
    public String del(@PathVariable("id") Long id){
        rabbitTemplate.convertAndSend(MqConstants.AXCMSM_EXCHANGE_TOPIC,MqConstants.AXCMSM_DELETE_KEY,id.toString());
        return "ok";
    }
}
