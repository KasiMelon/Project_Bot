package org.service.weather;


import java.net.http.HttpResponse;

import org.ui.WriterUI;

import org.ui.ConsoleCommandUI;

import org.json.JSONArray;
import org.json.JSONObject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    public static String output(HttpResponse<String> response) {
        if (response == null) {
            return ConsoleCommandUI.ErrorNetwork();
        }
        if (response.statusCode() == 200) {
            JSONObject jsonResponse = new JSONObject(response.body());

            JSONObject forecast = jsonResponse.getJSONObject("forecast");
            JSONObject location = jsonResponse.getJSONObject(("location"));
            String city = location.getString("name");
            JSONArray forecastday = forecast.getJSONArray("forecastday");

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < forecastday.length(); i++) {

                JSONObject targetDay = forecastday.getJSONObject(i);
                String date = targetDay.getString("date");
                JSONObject day = targetDay.getJSONObject("day");
                double avgTemp = day.getDouble("avgtemp_c");
                double humidity = day.getInt("avghumidity");
                JSONObject condition = day.getJSONObject("condition");
                String conditionText = condition.getString("text");

                WriterUI writer = new WriterUI(city, avgTemp, humidity, conditionText, date);
                result.append(writer).append("\n");
            }
            return result.toString();
        } else if (response.statusCode() == 400 || response.statusCode() == 404) {
            logger.error("Введено неверное название города");
            return ConsoleCommandUI.IncorrectCity();
        } else {
            logger.error("Возникла неполадка {}", response.statusCode());
            return ConsoleCommandUI.ErrorServer() + response.statusCode() + ").";
        }
    }
}