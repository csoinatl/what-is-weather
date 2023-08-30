package com.example.weather.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class WeatherForecastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer forecastId;

    private String forecast;
}
