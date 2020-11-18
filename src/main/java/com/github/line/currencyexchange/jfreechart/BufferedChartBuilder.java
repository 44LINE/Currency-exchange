package com.github.line.currencyexchange.jfreechart;

import com.github.line.currencyexchange.enums.ChartType;
import com.github.line.currencyexchange.service.BufferedImageBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BufferedChartBuilder extends BufferedImageBuilder {
    private final TimeSeries timeSeries;
    private final String chartName;
    private JFreeChart jFreeChart;

    private BufferedChartBuilder() {
        throw new AssertionError();
    }

    public BufferedChartBuilder(ChartType type, String code, TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
        this.chartName = type.name().replace("_", " ");

        this.jFreeChart = ChartFactory.createTimeSeriesChart(
                chartName,
                "Time period",
                code,
                getTimeSeriesCollection(),
                false,
                false,
                false
        );
    }

    @Override
    public BufferedImage build() {
        return jFreeChart.createBufferedImage(
                (this.width>0) ? this.width : this.DEFAULT_WIDTH,
                (this.height>0) ? this.height : this.DEFAULT_HEIGHT
        );
    }

    public BufferedChartBuilder addTrendLine() {
        TimeSeriesDataItem firstItem = this.timeSeries.getDataItem(0);
        TimeSeriesDataItem lastItem = this.timeSeries.getDataItem(this.timeSeries.getItemCount() - 1);
        double y1 = firstItem.getValue().doubleValue();
        double y2 = lastItem.getValue().doubleValue();

        XYLineAnnotation a = new XYLineAnnotation(
                firstItem.getPeriod().getFirstMillisecond(), y1,
                lastItem.getPeriod().getFirstMillisecond(), y2,
                new BasicStroke(0.9f), (y1>y2) ? Color.RED : Color.GREEN
        );

        XYPlot plot = (XYPlot) this.jFreeChart.getPlot();
        plot.addAnnotation(a);
        return this;
    }

    private TimeSeriesCollection getTimeSeriesCollection() {
        return new TimeSeriesCollection(this.timeSeries);
    }
}
