package com.example.weather;


import com.example.weather.adapter.Forecast;
import com.example.weather.adapter.ForecastGrid;
import com.example.weather.adapter.Geocode;
import com.example.weather.aggregator.WeatherSummaryAggregator;
import com.example.weather.api.geocode.GeocodeResponse;
import com.example.weather.api.weather.ForecastGridResponse;
import com.example.weather.api.weather.ForecastResponse;
import com.example.weather.model.WeatherSummary;
import org.springframework.web.reactive.function.client.WebClient;

public class WhatIsTheWeatherApp {

  private static final WebClient webClient = WebClient.create();

  public static void main(String[] args) {
    Geocode geocode = new Geocode(webClient);
    ForecastGrid forecastGrid = new ForecastGrid(webClient);
    Forecast forecast = new Forecast(webClient);

    GeocodeResponse geocodeResponse = geocode.getGeoCodeResponse("Cumming, GA, United States");
    ForecastGridResponse forecastGridResponse = forecastGrid.getForecastGridResponse(geocodeResponse);
    ForecastResponse forecastResponse = forecast.getForecastResponse(forecastGridResponse);

    WeatherSummary weatherSummary = WeatherSummaryAggregator.toWeatherSummary(forecastResponse);

    System.out.println(weatherSummary);
  }
}
