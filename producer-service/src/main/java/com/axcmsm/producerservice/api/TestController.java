package com.axcmsm.producerservice.api;

import com.axcmsm.producerservice.config.LoadProperties;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: com.axcmsm.producerservice.api.TestController
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/24
 */
@RequestMapping("/producer")
@RestController
@RefreshScope
public class TestController {

    @Value("${axcmsm.info}")
    private String info;

    @Resource
    private LoadProperties loadProperties;

    @GetMapping("/hello")
    public String hello(){
        return "hello!"+info+"\n"+loadProperties.getTip();
    }
}
