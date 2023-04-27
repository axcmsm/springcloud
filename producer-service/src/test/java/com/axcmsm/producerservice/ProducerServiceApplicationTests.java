package com.axcmsm.producerservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@SpringBootTest
class ProducerServiceApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息:普通消息队列
     */
    @Test
    void testSendToRabbitMQSimpleQueue() {
        String queueName = "simple.hello";//需要新建队列
        String message = "你好世界！";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    void testSendToRabbitMQSimpleFanoutExchange() {
        String exchangeName = "axcmsm.fanout";//交换机
        String message = "你好世界！"; //消息
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    void testSendToRabbitMQSimpleDirectExchange() {
        String exchangeName = "axcmsm.direct";//交换机
        String message = "你好 red ！"; //消息 发给 red yellow
        rabbitTemplate.convertAndSend(exchangeName, "red", message);
    }

    @Test
    void testSendToRabbitMQSimpleTopicExchange() {
        String exchangeName = "axcmsm.topic";//交换机
        String message = "你好 new axcmsm ！"; //消息 发给 axcmsm.new (一个收到)和 axcmsm.weather(2个收到）
        rabbitTemplate.convertAndSend(exchangeName, "axcmsm.new", message);
    }

    /**
     * 发送消息:消息确认机制,交换机失败回调
     */
    @Test
    void testSendToRabbitMQReturnBackQueue() {
        String exchangeName="simple.exchange";
        String queueName = "simple.hello";//需要新建队列
        String message = "你好世界！";
        //消息id
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //准备回调  成功回调，失败回调
        correlationData.getFuture().addCallback(confirm -> {
            if (confirm.isAck()) {
                //消息成功投递到交换机，返回ack
                log.debug("消息成功发送到交换机！消息ID{}",correlationData.getId());
            } else {
                // nack 消息未投递到交换机，返回nack
                log.error("消息投递到交换机失败！消息ID:{}",correlationData.getId());
                //可重发
            }
        }, throwable -> {
            // 记录日志, 消息发送过程中出现异常，没有收到回执
            log.error("消息发送失败", throwable);
            //可重发
        });
        rabbitTemplate.convertAndSend(exchangeName,queueName, message, correlationData);
    }

    /**
     * 发送消息:消息持久化
     */
    @Test
    void testSendToRabbitMQDurable() {
        String exchangeName="simple.exchange";
        String queueName = "simple.hello";//需要新建队列
        Message message = MessageBuilder.withBody("你好世界！".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT) //持久化 PERSISTENT
                .build();
        rabbitTemplate.convertAndSend(exchangeName,queueName, message);
    }
}
