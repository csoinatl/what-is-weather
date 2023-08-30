package com.example.weather.aggregator;

import com.example.weather.api.weather.ForecastResponse;
import com.example.weather.api.weather.ForecastResponsePeriod;
import com.example.weather.model.WeatherSummary;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WeatherSummaryAggregator {
    public static WeatherSummary toWeatherSummary(ForecastResponse forecastResponse) {
        var forecastPeriods = forecastResponse.getProperties().getPeriods();

        if (Objects.nonNull(forecastPeriods)) {

            Date time = forecastPeriods[0].getStartTime();
            int currentTemp = forecastPeriods[0].getTemperature();
            String currentForecast = forecastPeriods[0].getShortForecast();
            int maxTemp = Arrays.stream(forecastPeriods).mapToInt(ForecastResponsePeriod::getTemperature).max().getAsInt();
            double avgTemp = Arrays.stream(forecastPeriods).mapToInt(ForecastResponsePeriod::getTemperature).average().getAsDouble();
            List<String> forecasts = Arrays.stream(forecastPeriods).map(ForecastResponsePeriod::getShortForecast).collect(Collectors.toList());

            return WeatherSummary.builder()
                    .time(time)
                    .currentTemp(currentTemp)
                    .currentForecast(currentForecast)
                    .maxTemp(maxTemp)
                    .averageTemp(avgTemp)
                    .forecastList(forecasts)
                    .build();
        }

        return null;
    }
}
