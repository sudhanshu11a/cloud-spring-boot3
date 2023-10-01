package com.learning.cloud.microservices.currencyexchangeservice.controller;

import com.learning.cloud.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import com.learning.cloud.microservices.currencyexchangeservice.bean.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sudhanshu Sharma
 * on 25-09-2023
 */
@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from,
                                                  @PathVariable String to) {
        String port = environment.getProperty("local.server.port");
        //CurrencyExchange currencyExchange = new CurrencyExchange(1000l, from, to, BigDecimal.valueOf(50));
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if(currencyExchange ==null){
            throw new RuntimeException("Exchange not found!");
        }
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}
