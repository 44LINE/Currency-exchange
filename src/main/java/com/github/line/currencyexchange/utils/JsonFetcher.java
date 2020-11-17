package com.github.line.currencyexchange.utils;

import org.json.JSONObject;

import java.net.URI;
import java.net.URL;
import java.util.Optional;

public interface JsonFetcher {
    Optional<JSONObject> fetch(String url);
    Optional<JSONObject> fetch(URL url);
    Optional<JSONObject> fetch(URI uri);
}
