package pl.creazy.webapp01;

import org.jfree.data.time.Day;
import org.json.JSONObject;
import pl.creazy.webapp01.enums.ChartSeries;
import pl.creazy.webapp01.model.CurrencyExchange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;

/**
 * <h1>Stateless helper class with only static methods.</h1>
 * <p>These methods in the class work with JSON Objects returned from Alpha Vantage API.
 * No to inherit.</p>
 */
public final class AlphaVantageJsonMappingUtils {
    private AlphaVantageJsonMappingUtils() {}

    /**
     * Method takes {@link org.json.JSONObject} as a parameter and returns its keys.
     *
     * @param jsonObject {@link org.json.JSONObject}
     * @return {@link org.json.JSONObject}'s keys in {@link java.util.Iterator} format
     */
    public static Iterator<String> getJsonObjectKeys(JSONObject jsonObject) {
        return jsonObject.keys();
    }

    /**
     * Method fetches necessary data from Alpha Vantage's API returned {@link org.json.JSONObject}.
     * Using it and second param {@link java.lang.Double} instantiates {@link pl.creazy.webapp01.model.CurrencyExchange}.
     * This method works only with single currency ratio API requests
     *
     * @param amount     The amount to be exchanged
     * @param jsonObject {@link org.json.JSONObject}
     * @return instance of {@link pl.creazy.webapp01.model.CurrencyExchange}
     */
    public static CurrencyExchange parseJsonObjectToCurrencyExchange(double amount, JSONObject jsonObject) {
        JSONObject jo = jsonObject.getJSONObject("Realtime Currency Exchange Rate");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jo.getString("6. Last Refreshed"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new CurrencyExchange(
                jo.getString("1. From_Currency Code"),
                jo.getString("3. To_Currency Code"),
                amount,
                jo.getDouble("5. Exchange Rate"),
                date
        );
    }

    /**
     * Method fetches necessary data from Alpha Vantage's API returned {@link org.json.JSONObject}.
     * Second param indicates on which {@link java.lang.String} will be use as a key to work with provided {@link org.json.JSONObject}.
     * Values (ratios) are placed in {@link java.util.HashMap}.
     * {@link org.jfree.data.time.Day} objects serve as keys.
     * This method works only with time series API requests.
     *
     * @param chartSeries type of {@link pl.creazy.webapp01.enums.ChartSeries}
     * @param jsonObject  {@link org.json.JSONObject}
     * @return data in {@link java.util.HashMap} format
     */
    public static HashMap<Day, Double> parseJsonObjectToHashMap(ChartSeries chartSeries, JSONObject jsonObject) {
        HashMap<Day, Double> mappedSeries = new HashMap<>();
        String singleStateValueKey = (chartSeries.name().contains("ADJUSTED") && !chartSeries.name().contains("DAILY")) ? "5. adjusted close": "4. close";
        JSONObject timeSeries = jsonObject.getJSONObject(timeSeriesKeyFactoryMethod(chartSeries));
        Iterator<String> jsonKeys = timeSeries.keys();

        while (jsonKeys.hasNext()) {
            String key = jsonKeys.next();
            String[] dateSnippets = key.split("-");
            JSONObject singleState = timeSeries.getJSONObject(key);
            Day day = new Day(Integer.parseInt(dateSnippets[2]), Integer.parseInt(dateSnippets[1]), Integer.parseInt(dateSnippets[0]));
            mappedSeries.put(day, singleState.getDouble(singleStateValueKey));
        }

        return mappedSeries;
    }

    private static String timeSeriesKeyFactoryMethod(ChartSeries chartSeries) {
       switch (chartSeries) {
           case TIME_SERIES_DAILY:
           case TIME_SERIES_DAILY_ADJUSTED: return "Time Series (Daily)";
           case TIME_SERIES_WEEKLY: return "Weekly Time Series";
           case TIME_SERIES_WEEKLY_ADJUSTED: return "Weekly Adjusted Time Series";
           case TIME_SERIES_MONTHLY: return "Monthly Time Series";
           case TIME_SERIES_MONTHLY_ADJUSTED: return "Monthly Adjusted Time Series";
           default: return null;
       }
    }
}
