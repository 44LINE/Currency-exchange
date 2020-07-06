package pl.creazy.webapp01.model;

import java.util.Date;

/**
 * Represents currency exchanging process.
 * Simple state immutable class. No to inherit.
 * @author creazy
 * @version %I
 */
public final class CurrencyExchange {

    private String FROM_CURRENCY_CODE;
    private String TO_CURRENCY_CODE;

    private double FROM_CURRENCY_AMOUNT;
    private double TO_CURRENCY_AMOUNT;
    private double EXCHANGE_RATIO;

    private Date LAST_REFRESHED;

    private CurrencyExchange() {}

    /**
     * Instantiates a new CurrencyExchange.
     *
     * @param fromCurrencyCode   Currency's code from which it will be exchanged
     * @param toCurrencyCode     Currency's code to which it will be exchanged
     * @param fromCurrencyAmount Amount to exchange
     * @param exchangeRatio      Currency ratio
     * @param lastRefreshed      The date last API response
     */

    public CurrencyExchange(
        String fromCurrencyCode,
        String toCurrencyCode,
        double fromCurrencyAmount,
        double exchangeRatio,
        Date lastRefreshed
    ) {
        FROM_CURRENCY_CODE = fromCurrencyCode;
        TO_CURRENCY_CODE = toCurrencyCode;
        FROM_CURRENCY_AMOUNT = fromCurrencyAmount;
        EXCHANGE_RATIO = exchangeRatio;
        TO_CURRENCY_AMOUNT = fromCurrencyAmount*exchangeRatio;
        LAST_REFRESHED = lastRefreshed;
    }

    /**
     * Gets currency's code from which it will be exchanged
     *
     * @return Currency's code from which it will be exchanged
     */
    public String getFromCurrencyCode() {
        return FROM_CURRENCY_CODE;
    }

    /**
     * Gets currency's code to which it will be exchanged
     *
     * @return Currency's code to which it will be exchanged
     */
    public String getToCurrencyCode() {
        return TO_CURRENCY_CODE;
    }

    /**
     * Gets amount to exchange.
     *
     * @return Amount to exchange
     */
    public double getFromCurrencyAmount() {
        return FROM_CURRENCY_AMOUNT;
    }

    /**
     * Gets to currency amount.
     *
     * @return the to currency amount
     */
    public double getToCurrencyAmount() {
        return TO_CURRENCY_AMOUNT;
    }

    /**
     * Gets currency ratio.
     *
     * @return Currency ratio
     */
    public double getExchangeRatio() {
        return EXCHANGE_RATIO;
    }

    /**
     * Gets the date last API response.
     *
     * @return The date last API response
     */
    public Date getLastRefreshed() {
        return LAST_REFRESHED;
    }
}
