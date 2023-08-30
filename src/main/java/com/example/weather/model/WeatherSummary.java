package com.example.weather.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Builder
@Data
public class WeatherSummary {
    private Date time;
    private int currentTemp;
    private String currentForecast;
    private int maxTemp;
    private double averageTemp;
    private List<String> forecastList;

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",\n    ");
        getForecastList().forEach(stringJoiner::add);

        return "WeatherSummary{ \n" +
                "  Time = " + getTime() +
                ",\n  Current Temperature = " + getCurrentTemp() +
                ",\n  Current Forecast = " + getCurrentForecast() +
                ",\n  Expected Max Temperature = " + getMaxTemp() +
                ",\n  Expected Average Temperature = " + getAverageTemp() +
                ",\n  Expected Forecasts = [" + stringJoiner +
                "\n  ]\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (Objects.isNull(o) || getClass() != o.getClass()) {
            return false;
        }

        WeatherSummary other = (WeatherSummary) o;
        return this.getTime().equals(other.getTime());
    }
}
