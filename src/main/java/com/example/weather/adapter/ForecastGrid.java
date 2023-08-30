package com.example.weather.adapter;

import com.example.weather.constant.URLEndpoints;
import com.example.weather.api.geocode.GeocodeResponse;
import com.example.weather.api.weather.ForecastGridResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
public class ForecastGrid {
    private final WebClient webClient;

    public ForecastGridResponse getForecastGridResponse(GeocodeResponse geocodeResponse) {
        // Latitude and longitude comes back with 5 places after the decimal
        // this conversion will truncate anything after 4th place after the decimal
        String latitude = reduceScale(geocodeResponse.getLatt());
        String longitude = reduceScale(geocodeResponse.getLongt());

        String searchURL = String.format(URLEndpoints.WEATHER_URL, latitude, longitude);

        return webClient.get()
                .uri(searchURL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ForecastGridResponse.class)
                .block();
    }

    private String reduceScale(String valueToReduce) {
        return new BigDecimal(valueToReduce)
                .setScale(4, RoundingMode.HALF_UP)
                .toString();
    }
}
