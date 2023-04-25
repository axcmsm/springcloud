package com.axcmsm.consumerservice;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
