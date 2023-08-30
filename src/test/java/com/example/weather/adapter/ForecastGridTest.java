package com.example.weather.adapter;

import com.example.weather.api.geocode.GeocodeResponse;
import com.example.weather.api.weather.ForecastGridResponse;
import com.example.weather.constant.URLEndpoints;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ForecastGridTest {

    @Mock
    private WebClient webClientMock;
    @Mock
    private RequestHeadersUriSpec requestHeadersUriSpecMock;
    @Mock
    private RequestHeadersSpec requestHeadersSpecMock;
    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @SuppressWarnings("unchecked")
    @Test
    public void testGetForecastGridResponse() throws Exception {
        ForecastGridResponse forecastGridResponse = new ForecastGridResponse();

        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<ForecastGridResponse>>notNull())).thenReturn(Mono.just(new ForecastGridResponse()));

        ForecastGrid forecastGrid = new ForecastGrid(webClientMock);
        ForecastGridResponse response = forecastGrid.getForecastGridResponse(new GeocodeResponse());

        assertEquals(forecastGridResponse, response);
    }

    @Test
    public void testGetForecastGridResponseWhen404IsReturned() throws Exception {
        String searchURL = String.format(URLEndpoints.WEATHER_URL, "34.021741", "-84.271006");
        when(webClientMock.get()
                .uri(searchURL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ForecastGridResponse.class))
                .thenReturn(Mono.error(new RuntimeException(HttpStatus.NOT_FOUND.toString())));

        ForecastGrid forecastGrid = new ForecastGrid(webClientMock);
        ForecastGridResponse response = forecastGrid.getForecastGridResponse(new GeocodeResponse());

        assertNull(response);
    }

}