package com.github.line.currencyexchange.utils;

import java.time.format.DateTimeFormatter;

public class ExchangeKeys {
    public static final String FROM_CURRENCY_CODE = "1. From_Currency Code";
    public static final String TO_CURRENCY_CODE = "3. To_Currency Code";
    public static final String FROM_CURRENCY_NAME = "2. From_Currency Name";
    public static final String TO_CURRENCY_NAME = "4. To_Currency Name";
    public static final String EXCHANGE_RATE = "5. Exchange Rate";
    public static final String LAST_REFRESHED = "6. Last Refreshed";
    public static final String ASK_PRICE = "9. Ask Price";
    public static final String EXCHANGE_KEY = "Realtime Currency Exchange Rate";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private ExchangeKeys() {
        throw new AssertionError();
    }
}
