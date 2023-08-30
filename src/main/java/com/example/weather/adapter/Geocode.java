package com.example.weather.adapter;

import com.example.weather.constant.URLEndpoints;
import com.example.weather.api.geocode.GeocodeResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@AllArgsConstructor
public class Geocode {
    private final WebClient webClient;

    public GeocodeResponse getGeoCodeResponse(String location) {
        return webClient.get()
                .uri(URLEndpoints.GEOCODE_URL, Map.of("location", location))
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(GeocodeResponse.class)).block();
    }
}
