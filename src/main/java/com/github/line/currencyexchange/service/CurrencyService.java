package com.github.line.currencyexchange.service;

import com.github.line.currencyexchange.domain.Currency;
import com.github.line.currencyexchange.utils.JsonFetcher;
import com.sun.javafx.UnmodifiableArrayList;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class CurrencyService {
    private static final String AVAILABLE_CURRENCIES_URL =
            "http://openexchangerates.org/api/currencies.json";
    private final JsonCollectionParser<Currency> parser;
    private final JsonFetcher fetcher;


    public CurrencyService(@Autowired JsonCollectionParser<Currency> parser, @Autowired JsonFetcher fetcher) {
        this.parser = parser;
        this.fetcher = fetcher;
    }

    public List<Currency> getAll() {
        JSONObject jsonObject = fetcher.fetch(AVAILABLE_CURRENCIES_URL)
                .orElseThrow(RuntimeException::new);
        return (UnmodifiableArrayList<Currency>) parser.parseToCollection(jsonObject);
    }
}
