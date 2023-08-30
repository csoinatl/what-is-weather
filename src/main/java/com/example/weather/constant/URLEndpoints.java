package com.example.weather.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class URLEndpoints {
    public static final String GEOCODE_URL = "https://geocode.xyz/?locate={location}&json=1";
    public static final String WEATHER_URL = "https://api.weather.gov/points/%s,%s";
}
