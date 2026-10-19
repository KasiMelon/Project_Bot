package org.service.handler;


import java.net.URLEncoder;
import java.net.http.HttpResponse;
import org.ui.WriterUI;
import java.nio.charset.StandardCharsets;

import org.model.apiWeather.ApiWeather;
import org.ui.ConsoleCommandUI;

import org.json.JSONArray;
import org.json.JSONObject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final ConsoleCommandUI weatherInput = new ConsoleCommandUI();
    private final ApiWeather apiWeather = new ApiWeather();
    /**
     *формируется HTTP запрос на сервер ApiWeather
     * @param encodedCity запрашиваемый город
     * @param daysToRequest количество дней
     * @return
     */

    /**
     * метод запрашивает город у пользователя
     * @return
     */
    public static String getEncodingCity(Scanner scanner) {
        System.out.println("Введите город: ");
        String city;
        city = scanner.nextLine();
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        logger.info("Пользователь успешно ввёл название города {}", encodedCity);
        return encodedCity;
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
            WriterUI writerUI = new WriterUI(city, avgTemp, humidity, conditionText, date);
            return writerUI;
            /**
 * обработка возникших ошибок
 */
        } else if (response.statusCode() == 400 || response.statusCode() == 404) {
            logger.error("Введено неверное название города");
            return "Вы ввели некорректное название города, перепроверьте его и введите снова";
            ConsoleCommandUI.IncorrectCity();
            return null;
        } else {
            logger.error("Возникла неполадка {}", response.statusCode());
            return "Возникла ошибка на сервере погоды (Код: " + response.statusCode() + ").";
            ConsoleCommandUI.Error();
            return null;
        }
    }
}