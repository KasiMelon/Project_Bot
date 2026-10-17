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
     *
     * @return
     */
    public String getEncodingCity() {
        ConsoleCommandUI.InputCity();
        String city;
        city = weatherInput.userInput();
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        logger.info("Пользователь успешно ввёл название города {}", encodedCity);
        return encodedCity;
    }

    /**
     * метод запрашивает количество дней для прогноза погоды
     *
     * @return
     */
    public int getDaysToRequest() {
        while (true) {
            ConsoleCommandUI.InputDay();
            String input = weatherInput.userTrimInput();
            try {
                int daysAhead = Integer.parseInt(input);
                if (daysAhead >= 0 && daysAhead <= 7) {
                    logger.info("Пользователь ввёл корректно значение сдвига по дням {}", daysAhead);
                    return daysAhead;
                }
                logger.warn("Пользователь указал дни вне диапазона: {}", daysAhead);
                ConsoleCommandUI.IncorrectDay();
            } catch (NumberFormatException e) {
                logger.warn("Введено не числовое значение: {}", input);
                ConsoleCommandUI.IncorrectNumber();
            }
        }
    }

    /**
     * формируется ответ по полученной информации
     * @param response
     */
    public static WriterUI output(HttpResponse<String> response) {
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

            WriterUI writerUI = new WriterUI(city, avgTemp, humidity, conditionText, date);
            return writerUI;
            /**
 * обработка возникших ошибок
 */
        } else if (response.statusCode() == 400 || response.statusCode() == 404) {
            logger.error("Введено неверное название города");
            ConsoleCommandUI.IncorrectCity();
            return null;
        } else {
            logger.error("Возникла неполадка {}", response.statusCode());
            ConsoleCommandUI.Error();
            return null;
        }
    }
}