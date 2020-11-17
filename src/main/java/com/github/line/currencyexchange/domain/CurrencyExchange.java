package com.github.line.currencyexchange.domain;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.time.LocalDateTime;
import java.util.Objects;

@Immutable
public final class CurrencyExchange {

    private final Currency fromCurrency;
    private final Double toCurrency;
    private final Double givenAmount;
    private final Double returnAmount;
    private final Double exchangeRatio;
    private final LocalDateTime lastRefreshed;

    private CurrencyExchange() {
        throw new AssertionError();
    }

    public CurrencyExchange(Currency fromCurrency, Double toCurrency,
                            Double givenAmount, Double exchangeRatio, LocalDateTime lastRefreshed) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.givenAmount = givenAmount;
        this.exchangeRatio = exchangeRatio;
        this.returnAmount = countReturnAmount();
        this.lastRefreshed = lastRefreshed;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public Double getToCurrency() {
        return toCurrency;
    }

    public Double getGivenAmount() {
        return givenAmount;
    }

    public Double getReturnAmount() {
        return returnAmount;
    }

    public Double getExchangeRatio() {
        return exchangeRatio;
    }

    public LocalDateTime getLastRefreshed() {
        return lastRefreshed;
    }

    private final Double countReturnAmount() {
        return this.givenAmount * this.exchangeRatio;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof CurrencyExchange)){
            return false;
        }

        CurrencyExchange _obj = (CurrencyExchange) obj;
        return this.fromCurrency.equals(_obj.getFromCurrency())
                && this.toCurrency.equals(_obj.toCurrency)
                && (Double.compare(this.givenAmount, _obj.givenAmount) == 0)
                && (Double.compare(this.exchangeRatio, _obj.getExchangeRatio()) == 0)
                && this.lastRefreshed.isEqual(_obj.getLastRefreshed());
    }

    @Override
    public String toString() {
        return "CurrencyExchange{" +
                "fromCurrency=" + fromCurrency +
                ", toCurrency=" + toCurrency +
                ", givenAmount=" + givenAmount +
                ", returnAmount=" + returnAmount +
                ", exchangeRatio=" + exchangeRatio +
                ", lastRefreshed=" + lastRefreshed +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromCurrency, toCurrency, givenAmount, exchangeRatio, lastRefreshed);
    }
}
