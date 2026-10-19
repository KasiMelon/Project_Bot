package org.commands;

import org.example.Weather;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
    public String execute(String arguments) {
        if(arguments.isEmpty()) {
            return "Пожалуйста укажите город. Пример: /stats Москва";
        }

        String encodedCity = URLEncoder.encode(arguments, StandardCharsets.UTF_8);
        return Weather.output(Weather.Api(encodedCity,1));
    }
}