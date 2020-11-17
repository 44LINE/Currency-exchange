package com.github.line.currencyexchange.utils;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Optional;
import java.util.Scanner;

@Component
public class BasicJsonFetcher implements JsonFetcher{

    @Override
    public Optional<JSONObject> fetch(String url) {
        return fetch(getUrlFromString(url).get());
    }

    @Override
    public Optional<JSONObject> fetch(URI uri) {
        return fetch(uri.getPath());
    }

    @Override
    public Optional<JSONObject> fetch(URL url) {
        if (url == null) {
            return Optional.empty();
        }

        Optional<HttpURLConnection> httpURLConnection = openConnection(url);
        if (httpURLConnection.isPresent()) {
            if (validateConnection(httpURLConnection.get())) {
                return getJsonObject(url);
            }
        }
        return Optional.empty();
    }

    private final Optional<HttpURLConnection> openConnection(URL url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            return Optional.of(connection);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private final boolean validateConnection(HttpURLConnection connection) {
        try {
            return (connection.getResponseCode() == 200);
        } catch (IOException ioException) {
            return false;
        }
    }

    private final Optional<JSONObject> getJsonObject(URL url) {
        try {
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }
            scanner.close();
            return Optional.of(new JSONObject(String.valueOf(stringBuilder)));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private final Optional<URL> getUrlFromString(String string) {
        try {
            return Optional.of(new URL(string));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}