package com.github.line.currencyexchange.controller;

import com.github.line.currencyexchange.domain.CurrencyExchange;
import com.github.line.currencyexchange.service.CurrencyExchangeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
    private final CurrencyExchangeService service;

    public CurrencyExchangeController(CurrencyExchangeService service) {
        this.service = service;
    }

    @GetMapping(value = "/exchange/{fromCurrencyCode}/to/{toCurrencyCode}/{amount}")
    public CurrencyExchange exchange(@PathVariable String fromCurrencyCode, @PathVariable String toCurrencyCode,
                                     @PathVariable Double amount) {
        return service.exchangeCurrency(fromCurrencyCode, toCurrencyCode, amount);
    }
}
