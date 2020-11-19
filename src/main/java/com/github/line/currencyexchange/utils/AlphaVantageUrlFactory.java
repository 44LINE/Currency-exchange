package com.github.line.currencyexchange.utils;

import com.github.line.currencyexchange.enums.ChartType;

public class AlphaVantageUrlFactory {
    private static final String AVAILABLE_CURRENCIES_URL = "http://openexchangerates.org/api/currencies.json";
    private static final String[] EXCHANGE_URL =
            {"https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=",
             "&to_currency=",
             "&apikey="};
    private static final String[] TIME_SERIES_URL =
            {"https://www.alphavantage.co/query?function=",
             "&symbol=",
             "&apikey="};
    private static final String TIME_SERIES_PREFIX = "TIME_SERIES_";
    private static final String API_KEY = "7L4ELQA93Z21T5Q5";

    private AlphaVantageUrlFactory() {
        throw new AssertionError();
    }

    public static final String getAvailableCurrenciesUrl() {
        return AVAILABLE_CURRENCIES_URL;
    }

    public static final String getExchangeUrl(String from, String to) {
        return EXCHANGE_URL[0] + from + EXCHANGE_URL[1] + to + EXCHANGE_URL[2] + API_KEY;
    }

    public static final String getTimeSeriesUrl(String code, String type) {
        return TIME_SERIES_URL[0] + TIME_SERIES_PREFIX + type + TIME_SERIES_URL[1] + code + TIME_SERIES_URL[2] + API_KEY;
    }
}
