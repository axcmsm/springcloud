package com.axcmsm.producerservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: com.axcmsm.producerservice.config.LoadProperties
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/24
 */
@Data
@Component
@ConfigurationProperties(prefix = "axcmsm")
public class LoadProperties {
    //axcmsm.tip
    private String tip;
}
