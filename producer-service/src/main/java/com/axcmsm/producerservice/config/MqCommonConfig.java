package com.axcmsm.producerservice.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ClassName: com.axcmsm.producerservice.config.MqCommonConfig
 * 微信公众号：代码飞快
 * Description:
 * MQ 确认回调机制 return回调
 *
 * @author 须贺
 * @version 2023/4/27
 */
@Slf4j
@Component //全局唯一  //消息没有到达队列调用该回调
public class MqCommonConfig implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        //设置return回调
        rabbitTemplate.setReturnCallback((message, code, replyText, exchange, routingKey) -> {
            //记录日志 消息投递到交换机了，但是没有路由到队列。返回ack，及路由失败的原因
            log.info("消息发送到队列失败,应答码:{},原因:{},交换机:{},路由键:{},消息:{}",
                    code, replyText, exchange, routingKey, message.toString());
            //有需要的话进行消息的重发
        });

    }
}
