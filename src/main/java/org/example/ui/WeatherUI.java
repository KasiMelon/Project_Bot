package org.example.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.model.WeatherObjects.WeatherDTO;

public class WeatherUI {
    private static final Logger logger = LoggerFactory.getLogger(WeatherUI.class);
    private final String city;
    private final double avgTemp;
    private final double humidity;
    private final String conditionText;
    private final String date;

    public WeatherUI(WeatherDTO dto) {
        city = dto.city();
        avgTemp = dto.avgTemp();
        humidity = dto.humidity();
        conditionText = dto.conditionText();
        date = dto.date();
    }

    public String toLocalzedString(Long chatId) {
        return LanguageManager.getText(chatId, "date") + date + "\n" +
                LanguageManager.getText(chatId, "static") + city + "\n" +
                LanguageManager.getText(chatId, "temperature") + avgTemp + "°C\n" +
                LanguageManager.getText(chatId, "humidity") + humidity + "%\n" +
                LanguageManager.getText(chatId, "street") + conditionText + "\n";
    }
}