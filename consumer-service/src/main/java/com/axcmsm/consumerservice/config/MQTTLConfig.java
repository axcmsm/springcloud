package com.axcmsm.consumerservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: com.axcmsm.consumerservice.config.MQTTLConfig
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/27
 */
@Configuration
public class MQTTLConfig {
    @Bean
    public DirectExchange ttlExchange(){
        return new DirectExchange("ttl.direct");
    }
    @Bean
    public Queue ttlQueue(){
        return QueueBuilder.durable("ttl.queue")
                .ttl(10000)// 设置队列超时时间
                .deadLetterExchange("dl.direct") // 指定死信交换机
                .deadLetterRoutingKey("dl") // 指定key
                .build();
    }

    @Bean
    public Binding simpleBinding(){
        return BindingBuilder.bind(ttlQueue()).to(ttlExchange()).with("ttl");
    }
}
