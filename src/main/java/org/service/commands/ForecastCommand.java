package org.service.commands;

import org.service.weather.WeatherService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.ui.ConsoleCommandUI;
import org.model.apiWeather.ApiWeather;

public class ForecastCommand implements CommandExecute {
    @Override
    public boolean match(String input) {
        return input.equalsIgnoreCase("/forecast");

    }

    @Override
    public String execute(String arguments) {
        if (arguments.isEmpty()) {
            return ConsoleCommandUI.InputCityAndDays();
        }

        int lastSpaceIndex = arguments.lastIndexOf(" ");
        String city;
        int days = 1;

        if (lastSpaceIndex != -1) {
            city = arguments.substring(0, lastSpaceIndex).trim();
            String dayStr = arguments.substring(lastSpaceIndex + 1).trim();
            try {
                days = Integer.parseInt(dayStr);
                if (days < 0 || days > 7) {
                    return ConsoleCommandUI.IncorrectDay();
                }
            } catch (NumberFormatException e) {
                city = arguments;
            }
        } else {
            city = arguments;
        }
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        return WeatherService.output(ApiWeather.Api(encodedCity, days));
    }
}
