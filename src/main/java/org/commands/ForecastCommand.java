package org.commands;

import java.util.Scanner;

import org.example.WeatherService;

/**
 * ForecastCommand реализует вывод погоды через несколько дней.
 */
public class ForecastCommand implements Command {
    @Override
    public boolean Match(String input) {
        return input.equalsIgnoreCase("/forecast");
    }

    @Override
    public void execute(Scanner scanner) {
        String encodedCity = WeatherService.getEncodingCity(scanner);
        int daysToRequest = WeatherService.getDaysToRequest(scanner);
        WeatherService.output(WeatherService.Api(encodedCity, daysToRequest));
    }
}
