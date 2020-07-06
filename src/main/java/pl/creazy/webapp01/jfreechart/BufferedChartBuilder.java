package pl.creazy.webapp01.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import pl.creazy.webapp01.enums.ChartSeries;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Set;

/**
 * A builder for creating BufferedChart instances,
 * using JFreeChart library.
 * @author creazy
 * @version %I
 */
public class BufferedChartBuilder {

    private final int DEFAULT_WIDTH = 350;
    private final int DEFAULT_HEIGHT = 300;

    private JFreeChart jFreeChart;
    private int width, height;

    private BufferedChartBuilder() {}

    /**
     * Instantiates a new BufferedChartBuilder with the following params.
     *
     * @param code         The Currency code
     * @param chartSeries  The type of time series representing by a ChartSeries enum
     * @param providedData Received data (parsed from {@link org.json.JSONObject} to {@link java.util.HashMap}
     *                     by {@link pl.creazy.webapp01.AlphaVantageJsonMappingUtils}) from API Request.
     *                     Based on the data a {@link org.jfree.data.time.TimeSeries} and {@link org.jfree.chart.JFreeChart}
     *                     is built as well.
     * @param trendLine    This field decides about adding trend line to new created chart.
     */
    public BufferedChartBuilder(String code, ChartSeries chartSeries, HashMap<Day, Double> providedData, boolean trendLine) {
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;

        TimeSeries timeSeries = new TimeSeries(code);
        fillTimeSeries(timeSeries, providedData);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(timeSeries);

        jFreeChart = ChartFactory.createTimeSeriesChart(
                chartSeries.name().replace("_", " "),
                "Time period",
                code,
                dataset,
                false,
                false,
                false);

        if (trendLine) {
            TimeSeriesDataItem firstItem = timeSeries.getDataItem(0);
            TimeSeriesDataItem lastItem = timeSeries.getDataItem(timeSeries.getItemCount() - 1);
            double y1 = firstItem.getValue().doubleValue();
            double y2 = lastItem.getValue().doubleValue();

            XYLineAnnotation a = new XYLineAnnotation(
                    firstItem.getPeriod().getFirstMillisecond(),
                    y1,
                    lastItem.getPeriod().getFirstMillisecond(),
                    y2,
                    new BasicStroke(0.9f),
                    (y1>y2) ? Color.RED : Color.GREEN
            );

            XYPlot plot = (XYPlot) jFreeChart.getPlot();
            plot.addAnnotation(a);
        }
    }

    /**
     * Creates new instance of {@link java.awt.image.BufferedImage} from {@link org.jfree.chart.JFreeChart} object.
     *
     * @return new BufferedImage instance
     */
    public BufferedImage build() {
        return jFreeChart.createBufferedImage(width, height);
    }

    /**
     * Sets custom width of the new created BufferedImage.
     *
     * @param width the width
     * @return {@link pl.creazy.webapp01.jfreechart.BufferedChartBuilder}
     */
    public BufferedChartBuilder setWidth(int width) {
        if (width>0) {
            this.width = width;
        }
        return this;
    }

    /**
     * Sets custom height of the new created BufferedImage.
     *
     * @param height the height
     * @return {@link pl.creazy.webapp01.jfreechart.BufferedChartBuilder}
     */
    public BufferedChartBuilder setHeight(int height) {
        if (height>0) {
            this.height = height;
        }
        return this;
    }

    private TimeSeries fillTimeSeries(TimeSeries timeSeries, HashMap<Day, Double> data) {
        Set<Day> keys = data.keySet();
        for (Day key:
             keys) {
            timeSeries.add(key, data.get(key));
        }
        return timeSeries;
    }
}
