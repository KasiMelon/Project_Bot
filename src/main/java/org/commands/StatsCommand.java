package org.commands;

import java.util.Scanner;

import org.example.WeatherService;

/**
 * StatsCommand реализует вывод прогноза погоды на текущий день.
 */
public class StatsCommand implements Command {
    @Override
    public boolean Match(String input) {
        return input.equalsIgnoreCase("/stats");
    }

    @Override
    public void execute(Scanner scanner) {
        String encodedCity = WeatherService.getEncodingCity(scanner);
        int daysToRequest = 1;
        WeatherService.output(WeatherService.Api(encodedCity, daysToRequest));
    }
}