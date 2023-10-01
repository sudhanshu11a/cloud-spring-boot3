package com.learning.cloud.microservices.apigateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * @author Sudhanshu Sharma
 * on 25-09-2023
 */
@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRoute(RouteLocatorBuilder builder) {
        //Function<PredicateSpec, Buildable<Route>> routeFunction

        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
                .route(p-> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion"))
                .build();
    }

}
