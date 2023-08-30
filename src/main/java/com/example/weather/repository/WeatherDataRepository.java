package com.example.weather.repository;

import com.example.weather.model.WeatherSummary;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeatherDataRepository extends CrudRepository<WeatherSummary, Long> {
    List<WeatherSummary> findAllByAverageTempBetween(double low, double high);
}
