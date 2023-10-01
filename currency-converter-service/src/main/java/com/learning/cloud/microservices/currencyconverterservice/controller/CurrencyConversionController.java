package com.learning.cloud.microservices.currencyconverterservice.controller;

import com.learning.cloud.microservices.currencyconverterservice.bean.CurrencyConversion;
import com.learning.cloud.microservices.currencyconverterservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author Sudhanshu Sharma
 * on 25-09-2023
 */
@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyExchangeResponse = responseEntity.getBody();
        CurrencyConversion currencyConversion = new CurrencyConversion(currencyExchangeResponse.getId(), from, to, currencyExchangeResponse.getConversionMultiple(),
                quantity, quantity.multiply(currencyExchangeResponse.getConversionMultiple()), currencyExchangeResponse.getEnvironment()+"RestTemplate");
        //CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if (currencyConversion == null) {
            throw new RuntimeException("Exchange not found!");
        }
        return currencyConversion;
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion retrieveExchangeValueWithFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
       CurrencyConversion currencyExchangeResponse = currencyExchangeProxy.retrieveExchangeValue(from, to);
        CurrencyConversion currencyConversion = new CurrencyConversion(currencyExchangeResponse.getId(), from, to, currencyExchangeResponse.getConversionMultiple(),
                quantity, quantity.multiply(currencyExchangeResponse.getConversionMultiple()), currencyExchangeResponse.getEnvironment()+"Feign");
        if (currencyConversion == null) {
            throw new RuntimeException("Exchange not found!");
        }
        return currencyConversion;
    }

}
