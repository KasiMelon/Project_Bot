package org.example.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.model.WeatherObjects.WeatherDTO;

public class WeatherUI {
    private static final Logger logger = LoggerFactory.getLogger(WeatherUI.class);
    private final String city;
    private final double avgTemp;
    private final double humidity;
    private final String conditionText;
    private final String date;

    public WeatherUI(WeatherDTO dto) {
        city = dto.city();
        avgTemp = dto.avgTemp();
        humidity = dto.humidity();
        conditionText = dto.conditionText();
        date = dto.date();
    }


    @Override
    public String toString() {
        return "Дата: " + date + "\n" +
                " Статистика для города: " + city + "\n" +
                " Температура в среднем: " + avgTemp + "°C\n" +
                " Влажность: " + humidity + "%\n" +
                " На улице: " + conditionText + "\n";
    }
}