package com.axcmsm.consumerservice.api;

import com.axcmsm.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * ClassName: com.axcmsm.consumerservice.api.TestController
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/24
 */
@RestController
@RequestMapping("/consumer")
public class TestController {


    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/test")
    public String test() {
        String object = restTemplate.getForObject("http://producer-service/producer/hello", String.class);
        return "test:=>"+object;
    }

    @Resource
    private ProducerService producerService;


    @GetMapping("/hello")
    public String getData() {
        String hello = producerService.hello();
        return "test:=>"+hello;
    }
}
