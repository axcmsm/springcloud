package com.axcmsm.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName: com.axcmsm.gateway.filter.GlobalFilterConfig
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/25
 */
//@Order(-1)//过滤器优先级, -1 最早的那个，处理通过注解的方式还可以通过，实现Ordered，重写getOrder来设置优先级，俩种方式
@Component
public class GlobalFilterConfig implements GlobalFilter, Ordered {

    /**
     *
     * @param exchange 请求上下文，里面获取Request,Response等信息
     * @param chain   用来把请求委托给下一个过滤器链
     * @return    返回标示当前过滤器业务结束
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();

        //获取参数 authorization
        String auth = queryParams.getFirst("authorization");

        //判断参数值是否等于admin
        if ("admin".equals(auth)){
            //放行
            return chain.filter(exchange);
        }
        //拦截
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);//设置状态码，友好提示
        return exchange.getResponse().setComplete(); //进行拦截
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
