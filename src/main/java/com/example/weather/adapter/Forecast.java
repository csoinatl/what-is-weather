package com.example.weather.adapter;

import com.example.weather.api.weather.ForecastGridResponse;
import com.example.weather.api.weather.ForecastResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class Forecast {
    private final WebClient webClient;

    public ForecastResponse getForecastResponse(ForecastGridResponse forecastGridResponse) {
        String forecastUrl = forecastGridResponse.getProperties().getForecastHourly();
        Mono<ForecastResponse> forecastResponseMono =
                webClient.get()
                        .uri(forecastUrl)
                        .exchangeToMono(clientResponse -> clientResponse.bodyToMono(ForecastResponse.class));

        return forecastResponseMono.block();
    }
}
