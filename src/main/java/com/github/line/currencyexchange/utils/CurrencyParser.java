package com.github.line.currencyexchange.utils;

import com.github.line.currencyexchange.domain.Currency;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class CurrencyParser implements JsonCollectionParser<Currency>, JsonObjectParser<Currency, String>{
    @Override
    public Currency parseToObject(JSONObject jsonObject, String key) {
        String value = jsonObject.getString(key);
        return new Currency(key, value);
    }

    @Override
    public Collection<Currency> parseToCollection(JSONObject jsonObject) {
        List<Currency> currencies = new ArrayList<>();
        Iterator<String> keys = getKeys(jsonObject);

        while (keys.hasNext()) {
            currencies.add(parseToObject(jsonObject, keys.next()));
        }

        return Collections.unmodifiableList(currencies);
    }
}