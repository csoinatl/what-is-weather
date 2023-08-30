package com.example.weather.api.weather;

public class ForecastGridResponseProperties {

  private String forecastHourly;

  public String getForecastHourly() {
    return forecastHourly;
  }

  @Override
  public String toString() {
    return "ForecastGridResponseProperties{" +
        "forecastHourly='" + forecastHourly + '\'' +
        '}';
  }
}
