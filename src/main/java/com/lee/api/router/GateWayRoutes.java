package com.lee.api.router;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayRoutes {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes().route(
                r -> r.path("/java/**")
                        .filters(
                                f -> f.stripPrefix(1)
                        ).uri("http://www.baidu.com")
        ).build();
    }
}