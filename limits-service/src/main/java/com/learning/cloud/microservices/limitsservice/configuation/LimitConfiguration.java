package com.learning.cloud.microservices.limitsservice.configuation;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Sudhanshu Sharma
 * on 22-09-2023
 */
@Component
@ConfigurationProperties("limits-service")
public class LimitConfiguration {
    private int min;
    private int max;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
