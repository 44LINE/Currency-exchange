package com.github.line.currencyexchange.service;

import com.github.line.currencyexchange.enums.ChartType;
import com.github.line.currencyexchange.jfreechart.BufferedChartBuilder;
import com.github.line.currencyexchange.utils.JsonFetcher;
import org.jfree.data.time.TimeSeries;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class ChartService {
    private final JsonFetcher fetcher;
    private final JsonObjectParser<TimeSeries, ChartType> parser;

    public ChartService(@Autowired JsonFetcher jsonFetcher,
                        @Autowired JsonObjectParser<TimeSeries, ChartType> timeSeriesParser) {
        this.fetcher = jsonFetcher;
        this.parser = timeSeriesParser;
    }

    public BufferedImage getChart(String currencyCode, String chartType, boolean trendLine, int width, int height) {
        ChartType type = ChartType.valueOf(chartType);

        JSONObject jsonObject = this.fetcher.fetch("url")
                .orElseThrow(IllegalStateException::new);
        TimeSeries timeSeries = this.parser.parseToObject(jsonObject, type);

        BufferedChartBuilder bufferedChartBuilder = new BufferedChartBuilder(type, currencyCode, timeSeries);
        if (width > 0) {
            bufferedChartBuilder.setWidth(width);
        }
        if (height > 0) {
            bufferedChartBuilder.setHeight(height);
        }
        if (trendLine) {
            bufferedChartBuilder.addTrendLine();
        }

        return bufferedChartBuilder.build();
    }
}
