package org.service.commands;


import org.service.handler.WeatherService;
import org.model.apiWeather.ApiWeather;
/**
 * ForecastCommand реализует вывод погоды через несколько дней.
 */
public class ForecastCommand implements Command {
    @Override
    public boolean match(String input) {
        return input.equalsIgnoreCase("/forecast");
    }

    @Override
    public void execute() {
        WeatherService weatherService = new WeatherService();
        ApiWeather apiWeather = new ApiWeather();
        String encodedCity = weatherService.getEncodingCity();
        int daysToRequest = weatherService.getDaysToRequest();
        WeatherService.output(apiWeather.Api(encodedCity, daysToRequest));
    }
}
