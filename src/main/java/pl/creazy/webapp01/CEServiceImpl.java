package pl.creazy.webapp01;

import static pl.creazy.webapp01.APIConstants.*;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.creazy.webapp01.enums.ChartSeries;
import pl.creazy.webapp01.jfreechart.BufferedChartBuilder;
import pl.creazy.webapp01.jfreechart.BufferedImageInputStreamFactory;
import pl.creazy.webapp01.model.CurrencyExchange;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("ceService")
public class CEServiceImpl implements CurrencyExchangeService{
    private final JsonFetcher jsonFetcher;

    @Autowired
    public CEServiceImpl(@Qualifier("externalApiJsonFetcher") ExternalApiJsonFetcher jsonFetcher) {
        this.jsonFetcher = jsonFetcher;
    }

    @Override
    @Cacheable(value = "currencies")
    public List<String> getListOfCurrencies() {
        return iteratorToList(AlphaVantageJsonMappingUtils.getJsonObjectKeys(jsonFetcher.readJsonFromUrl(EXCHANGE_RATES_API_URL)));
    }

    @Override
    public CurrencyExchange prepareCurrencyExchange(String from, String to, double amount) {
        String alphaVantageCurrRatioRequestUrl =
                SINGLE_RATIO_PARTIAL_REQUEST_0 + from + SINGLE_RATIO_PARTIAL_REQUEST_1 + to + SINGLE_RATIO_PARTIAL_REQUEST_2 + API_KEY;

        return AlphaVantageJsonMappingUtils.parseJsonObjectToCurrencyExchange(amount, jsonFetcher.readJsonFromUrl(alphaVantageCurrRatioRequestUrl));
    }

    @Override
    public StreamResource getTimeChart(String code, ChartSeries chartSeries, int width, int height, boolean trendLine) {
        String alphaVantageTimeSeriesRequestUrl =
                TIME_SERIES_PARTIAL_REQUEST_0 + chartSeries.name() + TIME_SERIES_PARTIAL_REQUEST_1 + code + TIME_SERIES_PARTIAL_REQUEST_2 + API_KEY;

        BufferedImage bufferedChart = new BufferedChartBuilder
                        (code, chartSeries, AlphaVantageJsonMappingUtils.parseJsonObjectToHashMap(chartSeries, jsonFetcher.readJsonFromUrl(alphaVantageTimeSeriesRequestUrl)), trendLine)
                    .setWidth(width)
                    .setHeight(height)
                    .build();

        return new StreamResource(DEFAULT_CHART_NAME, new BufferedImageInputStreamFactory(bufferedChart));
    }
    
    private List<String> iteratorToList(Iterator<String> iterator) {
        List<String> list = new ArrayList<>();
        
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        
        return list;
    }
}
