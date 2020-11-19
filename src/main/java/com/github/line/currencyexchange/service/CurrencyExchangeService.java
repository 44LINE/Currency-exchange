package com.github.line.currencyexchange.service;

import com.github.line.currencyexchange.domain.CurrencyExchange;
import com.github.line.currencyexchange.utils.AlphaVantageUrlFactory;
import com.github.line.currencyexchange.utils.JsonFetcher;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {
    private final JsonFetcher fetcher;
    private final JsonObjectParser<CurrencyExchange, Double> parser;

    public CurrencyExchangeService(JsonFetcher fetcher, JsonObjectParser<CurrencyExchange, Double> parser) {
        this.fetcher = fetcher;
        this.parser = parser;
    }

    public CurrencyExchange exchangeCurrency(String fromCurrencyCode, String toCurrencyCode, Double amount) {
        JSONObject jsonObject = fetcher.fetch(AlphaVantageUrlFactory.getExchangeUrl(fromCurrencyCode, toCurrencyCode))
                .orElseThrow(IllegalStateException::new);
        return parser.parseToObject(jsonObject, amount);
    }
}
