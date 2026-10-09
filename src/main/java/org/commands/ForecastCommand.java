package org.commands;

import java.util.Scanner;

import org.example.Weather;

public class ForecastCommand implements CommandExecute {
    @Override
    public boolean Match(String input) {
        return input.equalsIgnoreCase("/forecast");
    }

    @Override
    public void execute(Scanner scanner) {
        String encodedCity = Weather.getEncodingCity(scanner);
        int daysToRequest = Weather.getDaysToRequest(scanner);
        Weather.output(Weather.Api(encodedCity, daysToRequest));
    }
}
