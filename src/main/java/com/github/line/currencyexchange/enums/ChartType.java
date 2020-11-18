package com.github.line.currencyexchange.enums;

public enum ChartType {
    DAILY("Time Series (Daily)", "4. close"),
    DAILY_ADJUSTED("Time Series (Daily)", "4. close"),
    WEEKLY("Weekly Time Series", "4. close"),
    WEEKLY_ADJUSTED("Weekly Adjusted Time Series", "5. adjusted close"),
    MONTHLY("Monthly Time Series", "4. close"),
    MONTHLY_ADJUSTED("Monthly Adjusted Time Series", "5. adjusted close");

    private final String timeSeriesJsonKey;
    private final String doubleValueJsonKey;

    ChartType(String timeSeriesJsonKey, String doubleValueJsonKey) {
        this.timeSeriesJsonKey = timeSeriesJsonKey;
        this.doubleValueJsonKey = doubleValueJsonKey;
    }

    public String getTimeSeriesJsonKey() {
        return timeSeriesJsonKey;
    }

    public String getDoubleValueJsonKey() {
        return doubleValueJsonKey;
    }
}
