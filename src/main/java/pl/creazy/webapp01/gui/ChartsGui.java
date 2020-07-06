package pl.creazy.webapp01.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.creazy.webapp01.CurrencyExchangeService;
import pl.creazy.webapp01.enums.ChartSeries;

import java.util.ArrayList;
import java.util.Collection;

@Route("charts")
public class ChartsGui extends VerticalLayout {
    public ChartsGui(@Qualifier("ceService") CurrencyExchangeService ceService) {

        Select<String> currencySelect = new Select<>();
        currencySelect.setLabel("Currency");
        currencySelect.setItems(ceService.getListOfCurrencies());

        Select<String> seriesTypeSelect = new Select<>();
        seriesTypeSelect.setLabel("Type");
        ArrayList<String> chartS = new ArrayList<>();
        for (ChartSeries cs:
             ChartSeries.values()) {
            chartS.add(cs.name());
        }
        seriesTypeSelect.setItems(chartS);

        Checkbox checkbox = new Checkbox();
        checkbox.setLabel("Trend line");
        checkbox.setValue(false);

        Button generateChartButton = new Button("Generate");
        TextArea textArea = new TextArea();
        textArea.setVisible(false);

        Image chart = new Image();
        chart.setVisible(false);

        add(currencySelect);
        add(seriesTypeSelect);
        add(checkbox);
        add(generateChartButton);
        add(textArea);
        add(chart);

        generateChartButton.addClickListener(event -> {
            if (currencySelect.isEmpty() || seriesTypeSelect.isEmpty()) {
                textArea.setVisible(true);
                textArea.setValue("Fill form!");
            } else {
                textArea.setVisible(false);
                chart.setSrc(ceService.getTimeChart(
                        currencySelect.getValue(),
                        ChartSeries.valueOf(seriesTypeSelect.getValue()),
                        1200,
                        800,
                        checkbox.getValue()));
                chart.setVisible(true);
            }
        });
    }
}
