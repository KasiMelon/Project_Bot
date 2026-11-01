package org.example.service.weather;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.example.model.WeatherObjects.WeatherDTO;

import org.example.ui.ConsoleCommandUI;

import org.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    public static WeatherDTO CreateDTO(HttpResponse<String> response) {
        if (response == null) {
            logger.error("Ошибка связи с сервером");
            throw new RuntimeException(ConsoleCommandUI.ErrorNetwork());
        }
        if (response.statusCode() == 200) {

            JSONObject jsonResponse = new JSONObject(response.body());

            JSONObject forecast = jsonResponse.getJSONObject("forecast");
            JSONObject location = jsonResponse.getJSONObject("location");
            String city = location.getString("name");
            JSONArray forecastday = forecast.getJSONArray("forecastday");

            JSONObject targetDay = forecastday.getJSONObject(0);
            String date = targetDay.getString("date");
            JSONObject day = targetDay.getJSONObject("day");
            double avgTemp = day.getDouble("avgtemp_c");
            double humidity = day.getInt("avghumidity");
            JSONObject condition = day.getJSONObject("condition");
            String conditionText = condition.getString("text");

            return new WeatherDTO(city, avgTemp, humidity, conditionText, date);

        } else if (response.statusCode() == 400 || response.statusCode() == 404) {
            logger.error("Введено неверное название города");
            throw new RuntimeException(ConsoleCommandUI.IncorrectCity());
        } else {
            logger.error("Возникла неполадка {}", response.statusCode());
            throw new RuntimeException(ConsoleCommandUI.ErrorServer() + response.statusCode() + ").");
        }
    }

    public static List<WeatherDTO> CreateForecastList(HttpResponse<String> response) {
        if (response == null) {
            logger.error("Ошибка связи с сервером");
            throw new RuntimeException(ConsoleCommandUI.ErrorNetwork());
        }
        if (response.statusCode() == 200) {

            JSONObject jsonResponse = new JSONObject(response.body());

            JSONObject forecast = jsonResponse.getJSONObject("forecast");
            JSONObject location = jsonResponse.getJSONObject("location");
            String city = location.getString("name");
            JSONArray forecastday = forecast.getJSONArray("forecastday");

            List<WeatherDTO> daysList = new ArrayList<>();

            for (int i = 0; i < forecastday.length(); i++) {

                JSONObject targetDay = forecastday.getJSONObject(i);
                String date = targetDay.getString("date");
                JSONObject day = targetDay.getJSONObject("day");
                double avgTemp = day.getDouble("avgtemp_c");
                double humidity = day.getInt("avghumidity");
                JSONObject condition = day.getJSONObject("condition");
                String conditionText = condition.getString("text");

                daysList.add(new WeatherDTO(city, avgTemp, humidity, conditionText, date));

            }
            return daysList;

        } else if (response.statusCode() == 400 || response.statusCode() == 404) {
            logger.error("Введено неверное название города");
            throw new RuntimeException(ConsoleCommandUI.IncorrectCity());
        } else {
            logger.error("Возникла неполадка {}", response.statusCode());
            throw new RuntimeException(ConsoleCommandUI.ErrorServer() + response.statusCode() + ").");
        }
    }
}