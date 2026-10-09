package org.commands;

import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Writer {
    private static final Logger logger = LoggerFactory.getLogger(Writer.class);
    private final String city;
    private final double avgTemp;
    private final double humidity;
    private final String conditionText;
    private final String date;

    public Writer(String city, double avgTemp, double humidity, String conditionText, String date) {
        this.city = city;
        this.avgTemp = avgTemp;
        this.humidity = humidity;
        this.conditionText = conditionText;
        this.date = date;
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
