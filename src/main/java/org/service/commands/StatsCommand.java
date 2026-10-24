package org.service.commands;

import org.model.apiWeather.ApiWeather;
import org.service.weather.WeatherService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.ui.ConsoleCommandUI;

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

        String encodedCity = URLEncoder.encode(arguments, StandardCharsets.UTF_8);
        return WeatherService.output(ApiWeather.Api(encodedCity, 1));
    }
}