package com.example.weather.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
public class WeatherSummaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date time;

    @Column(name = "current_temp")
    private Integer currentTemp;

    @Column(name = "current_forecast")
    private String currentForecast;

    @Column(name = "max_temp")
    private int maxTemp;

    @Column(name = "average_temp")
    private double averageTemp;

    @OneToMany(mappedBy = "forecastId")
    private List<WeatherForecastEntity> weatherForecastEntity;
}
