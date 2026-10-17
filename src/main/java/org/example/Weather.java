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

    /**
     *формируется HTTP запрос на сервер ApiWeather
     * @param encodedCity запрашиваемый город
     * @param daysToRequest количество дней
     * @return
     */
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



    /**
     * формируется ответ по полученной информации
     * @param response
     */
    public static String output(HttpResponse<String> response) {
        if(response == null) {
            return "Возникла ошибка в процессе установления связи с сервером погоды.";
        }
        if (response.statusCode() == 200) {
            JSONObject jsonResponse = new JSONObject(response.body());

            JSONObject forecast = jsonResponse.getJSONObject("forecast");
            JSONObject location = jsonResponse.getJSONObject(("location"));
            String city = location.getString("name");
            JSONArray forecastday = forecast.getJSONArray("forecastday");

            StringBuilder result = new StringBuilder();

            for(int i = 0;i<forecastday.length(); i++) {

                JSONObject targetDay = forecastday.getJSONObject(i);
                String date = targetDay.getString("date");
                JSONObject day = targetDay.getJSONObject("day");
                double avgTemp = day.getDouble("avgtemp_c");
                double humidity = day.getInt("avghumidity");
                JSONObject condition = day.getJSONObject("condition");
                String conditionText = condition.getString("text");

                Writer writer = new Writer(city, avgTemp, humidity, conditionText, date);
                result.append(writer.toString()).append("\n");
            }
            return result.toString();
/**
 * обработка возникших ошибок
 */
        } else if (response.statusCode() == 400 || response.statusCode() == 404) {
            logger.error("Введено неверное название города");
            return "Вы ввели некорректное название города, перепроверьте его и введите снова";
        } else {
            logger.error("Возникла неполадка {}", response.statusCode());
            return "Возникла ошибка на сервере погоды (Код: " + response.statusCode() + ").";
        }
    }
}