package com.axcmsm.producerservice.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName: com.axcmsm.producerservice.mq.Producer
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/25
 */
//@Component
public class Producer {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void send(String message) {
        this.rabbitTemplate.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
