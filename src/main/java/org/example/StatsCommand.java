package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONObject;

public class StatsCommand {
    public void execute(Scanner scanner) {
        System.out.println("Введите город: ");
        String city = scanner.nextLine();
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("ApiWeather");
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + encodedCity + "&appid=" + apiKey
                + "&lang=ru&units=metric";
        String response = getUrl(url);
        System.out.println(response);
        if (!response.isEmpty()) {
            JSONObject jsonObject = new JSONObject(response);
            System.out.println("Город: " + jsonObject.getString("name"));
            System.out.println("Температура: " + jsonObject.getJSONObject("main").getDouble("temp"));
            System.out.println("Влажность: " + jsonObject.getJSONObject("main").getInt("humidity"));
            System.out.println("Скорость ветра: " + jsonObject.getJSONObject("wind").getDouble("speed"));
            System.out.println("Направление ветра: " + jsonObject.getJSONObject("wind").getInt("deg"));
            System.out.println(
                    "Описание погоды: " + jsonObject.getJSONArray("weather").getJSONObject(0).getString("description"));
            System.out.println("Давление: " + jsonObject.getJSONObject("main").getDouble("pressure"));
        } else {
            System.out.println("Город не найден");
        }
    }

    private static String getUrl(String urlAdress) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URI(urlAdress).toURL();
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return result.toString();
    }
}