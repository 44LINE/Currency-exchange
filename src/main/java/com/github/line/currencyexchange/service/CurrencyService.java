package com.github.line.currencyexchange.service;

import com.github.line.currencyexchange.domain.Currency;
import com.github.line.currencyexchange.utils.AlphaVantageUrlFactory;
import com.github.line.currencyexchange.utils.JsonFetcher;
import com.sun.javafx.UnmodifiableArrayList;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class CurrencyService {
    private final JsonCollectionParser<Currency> parser;
    private final JsonFetcher fetcher;


    public CurrencyService(@Autowired JsonCollectionParser<Currency> parser, @Autowired JsonFetcher fetcher) {
        this.parser = parser;
        this.fetcher = fetcher;
    }

    public List<Currency> getAll() {
        JSONObject jsonObject = fetcher.fetch(AlphaVantageUrlFactory.getAvailableCurrenciesUrl())
                .orElseThrow(RuntimeException::new);
        return (List<Currency>) parser.parseToCollection(jsonObject);
    }
}
