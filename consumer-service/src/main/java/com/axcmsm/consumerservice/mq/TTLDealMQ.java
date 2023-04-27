package com.axcmsm.consumerservice.mq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: com.axcmsm.consumerservice.mq.TTLDealMQ
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/27
 */
@Component
public class TTLDealMQ {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "dl.queue",durable = "true"),
            exchange = @Exchange(name = "dl.direct"),
            key = "dl"
    ))
    public void listenDlQueue(String msg){
        System.out.println(msg);
    }
}
