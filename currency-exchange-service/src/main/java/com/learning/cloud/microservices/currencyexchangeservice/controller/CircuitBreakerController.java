package com.learning.cloud.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sudhanshu Sharma
 * on 25-09-2023
 */
@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/get_api1")
    @Retry( name="default", fallbackMethod = "hardcodedResponse")
    public String sampleApiWithRetry(){
        logger.info("Sample api call received");
        ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return entity.getBody();
    }

    @GetMapping("/get_api2")
    @CircuitBreaker(name="default", fallbackMethod = "hardcodedResponse")
    @RateLimiter(name="default")
    //@Bulkhead(name="default")
    public String sampleApiWithCircuitBreaker(){
        logger.info("Sample api request received!");
        ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return entity.getBody();
    }

    public String hardcodedResponse(Exception ex){
        logger.info("fallback-response called!");
        return "fallback-response";
    }
}
