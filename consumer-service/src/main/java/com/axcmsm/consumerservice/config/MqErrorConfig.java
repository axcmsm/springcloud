package com.axcmsm.consumerservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: com.axcmsm.consumerservice.config.MqConfigConfig
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/27
 */
@Configuration
public class MqErrorConfig {
    @Bean
    public DirectExchange errorMessageExchange(){
        return new DirectExchange("axcmsm.error.direct");
    }
    @Bean
    public Queue errorQueue(){
        return new Queue("error.queue",true);
    }
    @Bean
    public Binding errorBinding(){
        return BindingBuilder.bind(errorQueue()).to(errorMessageExchange()).with("error");
    }
    /**
     * 消息重发机制
     */
    @Bean
    public MessageRecoverer republishMessageRecoverer(RabbitTemplate rabbitTemplate){
        return new RepublishMessageRecoverer(rabbitTemplate,"axcmsm.error.direct","error");
    }
}
