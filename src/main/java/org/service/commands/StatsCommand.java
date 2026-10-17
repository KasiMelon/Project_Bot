package org.service.commands;


import org.service.handler.WeatherService;
import org.model.apiWeather.ApiWeather;

/**
 * StatsCommand реализует вывод прогноза погоды на текущий день.
 */
public class StatsCommand implements Command {
    @Override
    public boolean match(String input) {
        return input.equalsIgnoreCase("/stats");
    }

    @Override
    public void execute() {
        WeatherService weatherUI = new WeatherService();
        ApiWeather apiWeather = new ApiWeather();
        String encodedCity = weatherUI.getEncodingCity();
        int daysToRequest = 1;
        WeatherService.output(apiWeather.Api(encodedCity, daysToRequest));
    }
}