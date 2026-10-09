package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

public class Weather {
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
			return response;
		} catch (IOException | InterruptedException e) {
			System.out.println("Ошибка сети: " + e.getMessage());
		}
		return null;
	}

	public static String getEncodingCity(Scanner scanner) {
		System.out.println("Введите город: ");
		String city;
		city = scanner.nextLine();
		String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
		return encodedCity;
	}

	public static int getDaysToRequest(Scanner scanner) {
		int daysAhead;
		System.out.print("На сколько дней нужен будет прогноз? (0 - на сегодня, максимум на 7 дней): ");
		try {
			daysAhead = Integer.parseInt(scanner.nextLine().trim());
			if (daysAhead < 0 || daysAhead > 7) {
				System.out.println("Ошибка: Можно запросить прогноз от 0 до 7 дней. ");
				return 0;
			} else {
				return daysAhead;
			}
		} catch (NumberFormatException e) {
			System.out.println("Ошибка: необходимо ввести число. ");
		}
		return 0;
	}

	public static void output(HttpResponse<String> response) {
		if (response.statusCode() == 200) {
			System.out.println("Полный ответ от сервера:\n" + response.body());

			JSONObject jsonResponse = new JSONObject(response.body());

			JSONObject forecast = jsonResponse.getJSONObject("forecast");
			JSONArray forecastday = forecast.getJSONArray("forecastday");

			JSONObject targetDay = forecastday.getJSONObject(0);

			JSONObject day = targetDay.getJSONObject("day");
			double avgTemp = day.getDouble("avgtemp_c");
			int humidity = day.getInt("avghumidity");

			JSONObject condition = day.getJSONObject("condition");
			String conditionText = condition.getString("text");

			System.out.println("Температура в среднем: " + avgTemp + "°C");
			System.out.println("Влажность: " + humidity + "%");
			System.out.println("На улице: " + conditionText + "\n");
		} else {
			System.out.println("Город не найден");
		}
	}
}