package com.github.line.currencyexchange.controller;

import com.github.line.currencyexchange.service.ChartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChartController {
    private final ChartService service;

    public ChartController(ChartService service) {
        this.service = service;
    }

    @GetMapping(value = "/chart/draw/code/{currencyCode}/type/{chartType}/trend-line/{hasTrendLine}", produces = "image/png")
    public byte[] getChart(@PathVariable String currencyCode, @PathVariable String chartType, @PathVariable boolean hasTrendLine) {
        return service.getChart(currencyCode, chartType, hasTrendLine, 0, 0);
    }

    @GetMapping(value = "/chart/draw/code/{currencyCode}/type/{chartType}/trend-line/{hasTrendLine}/{width}/{height}", produces = "image/png")
    public byte[] getChartWithCustomSize(@PathVariable String currencyCode, @PathVariable String chartType,
                                         @PathVariable boolean hasTrendLine, @PathVariable int width, @PathVariable int height) {
        return service.getChart(currencyCode, chartType, hasTrendLine, width, height);
    }
}
