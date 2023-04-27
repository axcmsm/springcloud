package com.axcmsm.consumerservice;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@SpringBootTest
class ConsumerServiceApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息:普通消息队列
     */
    @Test
    void testSendToRabbitMQ() {
        String queueName="simple.queue";
        String message="hello, spring ampq!";
        rabbitTemplate.convertAndSend(queueName,message);
    }

    @Test
    void testTtlMsg(){
        Message message = MessageBuilder.withBody("延迟消息通知".getBytes(StandardCharsets.UTF_8))
                .setExpiration("5000")// 5秒  如果交换机也设置了超时时间，以最小的为准
                .build();
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("ttl.direct","ttl",message,correlationData);
    }



}
