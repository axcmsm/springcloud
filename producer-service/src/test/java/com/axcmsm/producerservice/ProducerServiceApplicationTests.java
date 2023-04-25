package com.axcmsm.producerservice;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProducerServiceApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 发送消息:普通消息队列
     */
    @Test
    void testSendToRabbitMQSimpleQueue() {
        String queueName="simple.hello";//需要新建队列
        String message="你好世界！";
        rabbitTemplate.convertAndSend(queueName,message);
    }

    @Test
    void testSendToRabbitMQSimpleFanoutExchange(){
        String exchangeName="axcmsm.fanout";//交换机
        String message="你好世界！"; //消息
        rabbitTemplate.convertAndSend(exchangeName,"",message);
    }

    @Test
    void testSendToRabbitMQSimpleDirectExchange(){
        String exchangeName="axcmsm.direct";//交换机
        String message="你好 red ！"; //消息 发给 red yellow
        rabbitTemplate.convertAndSend(exchangeName,"red",message);
    }

    @Test
    void testSendToRabbitMQSimpleTopicExchange(){
        String exchangeName="axcmsm.topic";//交换机
        String message="你好 new axcmsm ！"; //消息 发给 axcmsm.new (一个收到)和 axcmsm.weather(2个收到）
        rabbitTemplate.convertAndSend(exchangeName,"axcmsm.new",message);
    }
}
