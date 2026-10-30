package org.example.model.WeatherObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherDTO(
        String city,
        double avgTemp,
        double humidity,
        String conditionText,
        String date
) {
}