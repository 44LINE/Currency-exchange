package com.github.line.currencyexchange.jfreechart;

import com.github.line.currencyexchange.enums.ChartType;
import com.github.line.currencyexchange.service.JsonObjectParser;
import javafx.util.Pair;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class TimeSeriesParser implements JsonObjectParser<TimeSeries, ChartType> {
    private final String META_DATA_KEY = "Meta Data";
    private final String META_DATA_SYMBOL_KEY = "2. Symbol";
    private final String DATE_SPLITTER = "-";

    @Override
    public TimeSeries parseToObject(JSONObject jsonObject, ChartType type) {
        TimeSeries timeSeries = new TimeSeries(getCurrencySymbol(jsonObject));
        for (Pair<Day, Double> pair: getValues(jsonObject, type)) {
            timeSeries.add(pair.getKey(), pair.getValue());
        }
        return timeSeries;
    }

    private final List<Pair<Day, Double>> getValues(JSONObject jsonObject, ChartType type) {
        List<Pair<Day, Double>> values = new ArrayList<>();
        JSONObject jsonListObject = jsonObject.getJSONObject(type.getTimeSeriesJsonKey());

        Iterator<String> jsonKeys = jsonListObject.keys();
        while (jsonKeys.hasNext()) {
            String key = jsonKeys.next();
            Double ratio = jsonListObject.getJSONObject(key).getDouble(type.getDoubleValueJsonKey());
            values.add(new Pair(parseStringToDay(key), ratio));
        }
        return values;
    }

    private Day parseStringToDay(String string) {
        String[] snippets = string.split(DATE_SPLITTER);
        int day = Integer.parseInt(snippets[2]);
        int month = Integer.parseInt(snippets[1]);
        int year = Integer.parseInt(snippets[0]);
        return new Day(day, month, year);
    }

    private String getCurrencySymbol(JSONObject jsonObject) {
        JSONObject metaData = jsonObject.getJSONObject(META_DATA_KEY);
        return metaData.getString(META_DATA_SYMBOL_KEY);
    }
}
