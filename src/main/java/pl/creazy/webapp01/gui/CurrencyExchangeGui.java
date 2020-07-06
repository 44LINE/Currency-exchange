package pl.creazy.webapp01.gui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.creazy.webapp01.CurrencyExchangeService;
import pl.creazy.webapp01.model.CurrencyExchange;

import java.util.Collection;

@Route("currency-exchange")
public class CurrencyExchangeGui extends VerticalLayout{

    public CurrencyExchangeGui(@Qualifier("ceService") CurrencyExchangeService ceService) {

        Select<String> fromSelect = new Select<>();
        fromSelect.setLabel("From");
        fromSelect.setItems(ceService.getListOfCurrencies());

        Select<String> toSelect = new Select<>();
        toSelect.setLabel("To");
        toSelect.setItems(ceService.getListOfCurrencies());

        NumberField amountField = new NumberField();
        amountField.setLabel("Amount");

        Button countButton = new Button("COUNT");
        Button exchangeButton = new Button("EXCHANGE");
        exchangeButton.setVisible(false);

        TextArea textArea = new TextArea();
        textArea.setReadOnly(true);
        textArea.setVisible(false);

        add(fromSelect);
        add(toSelect);
        add(amountField);
        add(countButton);
        add(textArea);
        add(exchangeButton);

        countButton.addClickListener(event -> {
            textArea.setVisible(true);

            if (fromSelect.isEmpty() || toSelect.isEmpty() || amountField.isEmpty()) {
                textArea.setValue("Fill form!");
            } else {
                CurrencyExchange cExchange = ceService.prepareCurrencyExchange(
                        fromSelect.getValue(),
                        toSelect.getValue(),
                        amountField.getValue()
                );
                textArea.setValue(
                        "From: " + cExchange.getFromCurrencyCode()
                        + "\nTo: " + cExchange.getToCurrencyCode()
                        + "\nAmount: " + cExchange.getToCurrencyAmount()
                        + "\nRatio: " + cExchange.getExchangeRatio()
                        + "\nData: " + cExchange.getLastRefreshed()
                );
                exchangeButton.setVisible(true);
            }
        });

        exchangeButton.addClickListener(event -> {
            //todo
            UI.getCurrent().getPage().reload();
        });
    }
}

