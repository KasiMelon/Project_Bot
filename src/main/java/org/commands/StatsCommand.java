package org.commands;

import java.util.Scanner;

import org.example.Weather;

/**
 * класс отвечает за обработкку команды /stats
 * метод Match сравнивает введёный текст с /stats
 * метод execute получет введёный город и выводит полученный данные прогноза на один день
 */
public class StatsCommand implements CommandExecute {
    @Override
    public boolean Match(String input) {
        return input.equalsIgnoreCase("/stats");
    }

    @Override
    public void execute(Scanner scanner) {
        String encodedCity = Weather.getEncodingCity(scanner);
        int daysToRequest = 1;
        Weather.output(Weather.Api(encodedCity, daysToRequest));
    }
}