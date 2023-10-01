package com.learning.cloud.microservices.currencyconverterservice.proxy;

import com.learning.cloud.microservices.currencyconverterservice.bean.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Sudhanshu Sharma
 * on 25-09-2023
 */
//@FeignClient(name = "currency-exchange", url = "localhost:8001")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from,
                                                  @PathVariable String to) ;
}
