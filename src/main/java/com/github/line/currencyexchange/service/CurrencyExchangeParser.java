package com.github.line.currencyexchange.service;

import static com.github.line.currencyexchange.utils.ExchangeKeys.*;
import com.github.line.currencyexchange.domain.Currency;
import com.github.line.currencyexchange.domain.CurrencyExchange;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CurrencyExchangeParser implements JsonObjectParser<CurrencyExchange, Double> {

    @Override
    public CurrencyExchange parseToObject(JSONObject jsonObject, Double amount) {
        LocalDateTime lastRefreshed = LocalDateTime.parse(jsonObject.getString(LAST_REFRESHED), DATE_FORMATTER);
        Currency from = new Currency(jsonObject.getString(FROM_CURRENCY_NAME), jsonObject.getString(FROM_CURRENCY_CODE));
        Currency to = new Currency(jsonObject.getString(TO_CURRENCY_NAME), jsonObject.getString(TO_CURRENCY_CODE));

        return new CurrencyExchange(from, to, amount, jsonObject.getDouble(ASK_PRICE), lastRefreshed);
    }
}
