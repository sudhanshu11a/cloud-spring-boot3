package com.learning.cloud.microservices.limitsservice.controller;

import com.learning.cloud.microservices.limitsservice.beans.Limits;
import com.learning.cloud.microservices.limitsservice.configuation.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sudhanshu Sharma
 * on 22-09-2023
 */
@RestController
public class LimitsController {

    @Autowired
    private LimitConfiguration limitConfiguration;

    @GetMapping("/limits")
    public Limits getLimits(){
        return new Limits(limitConfiguration.getMin(), limitConfiguration.getMax());
    }

}
