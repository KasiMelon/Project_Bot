package org.model.apiWeather;

import org.model.env.EnvConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ui.ConsoleCommandUI;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class ApiWeather {
    private static final Logger logger = LoggerFactory.getLogger(ApiWeather.class);
    private final ConsoleCommandUI weatherInput = new ConsoleCommandUI();

    public static HttpResponse<String> Api(String encodedCity, int daysToRequest) {
        String apiKey = EnvConfig.getInstance().getApiWeather();
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
            ConsoleCommandUI.NetworkError();
        }
        return null;
    }
}