package com.github.line.currencyexchange.service;

import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;

public interface JsonCollectionParser<T> {
    default Iterator<String> getKeys(JSONObject jsonObject) {
        return jsonObject.keys();
    }
    Collection<T> parseToCollection(JSONObject jsonObject);
}
