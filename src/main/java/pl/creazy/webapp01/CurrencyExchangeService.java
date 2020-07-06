package pl.creazy.webapp01;

import com.vaadin.flow.server.StreamResource;
import pl.creazy.webapp01.enums.ChartSeries;
import pl.creazy.webapp01.model.CurrencyExchange;

import java.util.List;

public interface CurrencyExchangeService {
    List<String> getListOfCurrencies();
    CurrencyExchange prepareCurrencyExchange(String from, String to, double amount);
    StreamResource getTimeChart(String code, ChartSeries chartSeries, int width, int height, boolean trendLine);
}
