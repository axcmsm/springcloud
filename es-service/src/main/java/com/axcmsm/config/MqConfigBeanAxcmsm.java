package com.axcmsm.config;

import com.axcmsm.constants.MqConstants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: com.axcmsm.config.MqConfigBeanAxcmsm
 * 微信公众号：代码飞快
 * Description:
 * 绑定规则
 * @author 须贺
 * @version 2023/4/27
 */
@Configuration
public class MqConfigBeanAxcmsm {
    /**
     * 交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        //durable：指定 Exchange 是否需要持久化存储。如果设置为 true，Exchange 会在 RabbitMQ 中存储，并且在服务器重启后仍然存在；如果设置为 false，则 Exchange 不会被存储。
        //autoDelete：指定是否自动删除该 Exchange。如果设置为 true，则表示当 Exchange 没有被任何队列或交换机使用时会自动删除；如果设置为 false，则 Exchange 在不使用时不会自动删除。
        return new TopicExchange(MqConstants.AXCMSM_EXCHANGE_TOPIC,true,false);
    }

    /**
     * 新增队列
     * @return
     */
    @Bean
    public Queue insertQueue(){
//        return new Queue(MqConstants.AXCMSM_INSERT_QUEUE,true);
        return QueueBuilder.durable(MqConstants.AXCMSM_INSERT_QUEUE).build();
    }

    /**
     * 删除队列
     * @return
     */
    @Bean
    public Queue deleteQueue(){
        return new Queue(MqConstants.AXCMSM_DELETE_QUEUE,true);
    }

    /**
     * 绑定key  新增
     * @return
     */
    @Bean
    public Binding insertBinding(){
        return BindingBuilder.bind(insertQueue()).to(topicExchange()).with(MqConstants.AXCMSM_INSERT_KEY);
    }

    /**
     * 绑定key  删除
     * @return
     */
    @Bean
    public Binding deleteBinding(){
        return BindingBuilder.bind(deleteQueue()).to(topicExchange()).with(MqConstants.AXCMSM_DELETE_KEY);
    }
}
