package org.example.service.commands;

import org.example.service.weather.GetWeatherWithApi;
import org.example.service.weather.WeatherService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.example.ui.ConsoleCommandUI;
import org.example.ui.WeatherUI;

public class StatsCommand implements CommandExecute {
    @Override
    public boolean match(String input) {

        return input.equalsIgnoreCase("/stats");
    }

    @Override
    public String execute(String arguments) {
        if (arguments.isEmpty()) {
            return ConsoleCommandUI.InputCity();
        }

        String encodedQuery = URLEncoder.encode(arguments, StandardCharsets.UTF_8).replace("%2C", ",");
        WeatherUI weather = new WeatherUI(WeatherService.CreateDTO(GetWeatherWithApi.GetWeatherInfo(encodedQuery, 1)));
        return weather.toString();
    }
}