package com.github.line.currencyexchange.service;

import org.json.JSONObject;

public interface JsonObjectParser<T, S> {
    T parseToObject(JSONObject jsonObject, S param);
}
