package com.github.line.currencyexchange.utils;

import com.github.line.currencyexchange.domain.Currency;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;

public interface JsonParser<T> {
    default Iterator<String> getKeys(JSONObject jsonObject) {
        return jsonObject.keys();
    }

    T parseToObject(JSONObject jsonObject, String key);
    Collection<T> parseToCollection(JSONObject jsonObject);
}
