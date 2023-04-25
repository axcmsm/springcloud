package com.axcmsm.consumerservice.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ClassName: com.axcmsm.consumerservice.mq.Consumer
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/25
 */
@Component
public class Consumer {
    @RabbitListener(queues = "simple.hello")
    public void receive(String message) {
        System.out.println(" [x] Received '" + message + "'");
    }

    @RabbitListener(queues = "fanout.queue1")
    public void fanoutQueue1(String message) {
        System.out.println("fanoutQueue1:=>" + message );
    }
    @RabbitListener(queues = "fanout.queue2")
    public void fanoutQueue2(String message) {
        System.out.println("fanoutQueue2:=>"+message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "axcmsm.direct",type = ExchangeTypes.DIRECT),
            key = {"red","blue"}
    ))
    public void listenDirectQueue1(String msg){
        System.out.println("消费者接收到direct.queue1的消息：【"+msg+"】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "axcmsm.direct",type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}
    ))
    public void listenDirectQueue2(String msg){
        System.out.println("消费者接收到direct.queue2的消息：【"+msg+"】");
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "topic.queue1"),
                    exchange = @Exchange(name = "axcmsm.topic",type = ExchangeTypes.TOPIC),
                    key = "axcmsm.#"
            )
    )
    public void listenTopicQueue1(String msg){
        System.out.println("消费者接收到topic.queue1的消息：【"+msg+"】");
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "topic.queue2"),
                    exchange = @Exchange(name = "axcmsm.topic",type = ExchangeTypes.TOPIC),
                    key = "#.weather"
            )
    )
    public void listenTopicQueue2(String msg){
        System.out.println("消费者接收到topic.queue2的消息：【"+msg+"】");
    }



}
