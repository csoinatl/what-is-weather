package com.example.weather.api.weather;

public class ForecastGridResponse {

  private ForecastGridResponseProperties properties;

  public ForecastGridResponseProperties getProperties() {
    return properties;
  }

  @Override
  public String toString() {
    return "ForecastGridResponse{" +
        "properties=" + properties +
        '}';
  }
}
