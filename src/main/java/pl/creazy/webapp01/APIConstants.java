package pl.creazy.webapp01;

public class APIConstants {
    private APIConstants() {}

    static final String EXCHANGE_RATES_API_URL = "http://openexchangerates.org/api/currencies.json";
    static final String SINGLE_RATIO_PARTIAL_REQUEST_0 = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=";
    static final String SINGLE_RATIO_PARTIAL_REQUEST_1 = "&to_currency=";
    static final String SINGLE_RATIO_PARTIAL_REQUEST_2 = "&apikey=";
    static final String TIME_SERIES_PARTIAL_REQUEST_0 = "https://www.alphavantage.co/query?function=";
    static final String TIME_SERIES_PARTIAL_REQUEST_1 = "&symbol=";
    static final String TIME_SERIES_PARTIAL_REQUEST_2 = "&apikey=";
    static final String API_KEY = "7L4ELQA93Z21T5Q5";

    static final String DEFAULT_CHART_NAME = "chart.png";
}
