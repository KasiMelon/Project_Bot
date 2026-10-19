package org.commands;

import org.example.Weather;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
    public String execute(String arguments) {
        if(arguments.isEmpty()) {
            return "Укажите город и количество дней. Пример: /forecast Москва 3";
        }

        int lastSpaceIndex = arguments.lastIndexOf(" ");
        String city;
        int days = 1;

        if(lastSpaceIndex != -1) {
            city = arguments.substring(0,lastSpaceIndex).trim();
            String dayStr = arguments.substring(lastSpaceIndex + 1).trim();
            try {
                days = Integer.parseInt(dayStr);
                if(days < 0 || days > 7) {
                    return "Прогноз можно запросить только от 0 до 7 дней.";
                }
            } catch(NumberFormatException e) {
                city = arguments;
            }
        } else {
            city = arguments;
        }
        String encodedCity = URLEncoder.encode(city,StandardCharsets.UTF_8);
        return Weather.output(Weather.Api(encodedCity,days));
    }
}
