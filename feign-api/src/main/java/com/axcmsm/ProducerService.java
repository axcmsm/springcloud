package com.axcmsm;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient("producer-service")
public interface ProducerService {
    @GetMapping("/producer/hello")
    String hello();
}
