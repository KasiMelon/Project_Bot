package org.example.service.commands;

import org.example.service.weather.GetWeatherWithApi;
import org.example.service.weather.WeatherService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.example.ui.ConsoleCommandUI;
import org.example.ui.WeatherUI;

import java.util.List;

public class ForecastCommand implements CommandExecute {
    @Override
    public boolean match(String input) {
        return input.equalsIgnoreCase("/forecast");

    }

    @Override
    public String execute(String arguments,Long chatId) {
        if (arguments.isEmpty()) {
            return ConsoleCommandUI.InputCityAndDays(chatId);
        }

        int lastSpaceIndex = arguments.lastIndexOf(" ");
        String city;
        int days = 1;

        if (lastSpaceIndex != -1) {
            city = arguments.substring(0, lastSpaceIndex).trim();
            String dayStr = arguments.substring(lastSpaceIndex + 1).trim();
            try {
                days = Integer.parseInt(dayStr);
                if (days < 0 || days > 3) {
                    return ConsoleCommandUI.IncorrectDay(chatId);
                }
            } catch (NumberFormatException e) {
                city = arguments;
            }
        } else {
            city = arguments;
        }
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        var response = GetWeatherWithApi.GetWeatherInfo(encodedCity, days,chatId);

        List<org.example.model.WeatherObjects.WeatherDTO> dtoList = WeatherService.CreateForecastList(response,chatId);

        StringBuilder result = new StringBuilder();

        for (org.example.model.WeatherObjects.WeatherDTO dto : dtoList) {
            WeatherUI weather = new WeatherUI(dto);
            result.append(weather.toLocalzedString(chatId)).append("\n\n");
        }
        return result.toString();
    }
}
