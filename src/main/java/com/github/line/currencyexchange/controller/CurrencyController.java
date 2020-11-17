package com.github.line.currencyexchange.controller;

import com.github.line.currencyexchange.domain.Currency;
import com.github.line.currencyexchange.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {
    private final CurrencyService service;

    public CurrencyController(@Autowired CurrencyService currencyService) {
        this.service = currencyService;
    }

    @GetMapping(value = "/available-currencies")
    public List<Currency> all() {
        return service.getAll();
    }
}
