package org.example;

import java.io.IOException;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.commands.Writer;

import org.json.JSONArray;
import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Weather {
    private static final Logger logger = LoggerFactory.getLogger(Weather.class);

    public static HttpResponse<String> Api(String encodedCity, int daysToRequest) {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("ApiWeather");
        try {
            String url = "http://api.weatherapi.com/v1/forecast.json?key=" + apiKey
                    + "&q=" + encodedCity
                    + "&days=" + daysToRequest
                    + "&lang=ru";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.debug("Запрос успешно сформирован {}", response);
            return response;
        } catch (IOException | InterruptedException e) {
            logger.error("Ошибка сети: {}", e.getMessage(), e);
            System.out.println("Возникла ошибка в процессе установления связи");
        }
        return null;
    }

    public static String getEncodingCity(Scanner scanner) {
        System.out.println("Введите город: ");
        String city;
        city = scanner.nextLine();
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        logger.info("Пользователь успешно ввёл название города {}", encodedCity);
        return encodedCity;
    }

    public static int getDaysToRequest(Scanner scanner) {
        while (true) {
            System.out.print("На сколько дней нужен будет прогноз? (0 - на сегодня, максимум на 7 дней): ");
            String input = scanner.nextLine().trim();
            try {
                int daysAhead = Integer.parseInt(input);
                if (daysAhead >= 0 && daysAhead <= 7) {
                    logger.info("Пользователь ввёл корректно значение сдвига по дням {}", daysAhead);
                    return daysAhead;
                }
                logger.warn("Пользователь указал дни вне диапазона: {}", daysAhead);
                System.out.println("Прогноз можно запросить только в диапазоне от 0 до 7 дней. Введите диапазон снова.\n");
            } catch (NumberFormatException e) {
                logger.warn("Введено не числовое значение: {}", input);
                System.out.println("Необходимо ввести числовое значение. Попробуйте ещё раз.\n");
            }
        }
    }

    public static void output(HttpResponse<String> response) {
        if (response.statusCode() == 200) {
            logger.debug("Успешно получен ответ от сервера {}", response);

            JSONObject jsonResponse = new JSONObject(response.body());

            JSONObject forecast = jsonResponse.getJSONObject("forecast");
            JSONObject location = jsonResponse.getJSONObject(("location"));
            String city = location.getString("name");
            JSONArray forecastday = forecast.getJSONArray("forecastday");

            JSONObject targetDay = forecastday.getJSONObject(0);
            String date = targetDay.getString("date");

            JSONObject day = targetDay.getJSONObject("day");
            double avgTemp = day.getDouble("avgtemp_c");
            double humidity = day.getInt("avghumidity");

            JSONObject condition = day.getJSONObject("condition");
            String conditionText = condition.getString("text");

            Writer writer = new Writer(city, avgTemp, humidity, conditionText, date);
            System.out.println(writer);
            logger.info("Чат-бот успешно вывел запрашиваемые данные");

        } else if (response.statusCode() == 400 || response.statusCode() == 404) {
            logger.error("Введено неверное название города");
            System.out.println("Вы ввели некорректное название города, перепроверьте его и введите снова");
        } else {
            logger.error("Возникла неполадка {}", response.statusCode());
            System.out.println("Возникла ошибка");
        }
    }
}