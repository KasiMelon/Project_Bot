package org.commands;

import java.util.Scanner;

import org.example.Weather;

/**
 * данный класс отвечает за обработку команды /forecast
 * метод Match проверяет совпадает ли введённый текст с /forecast
 * метод execute получает название города и сдвиг по дням. а затем передаёт собранные данные в метод Weather.Api для выполнения HTTP запроса
 */
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
