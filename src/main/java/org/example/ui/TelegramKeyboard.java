package org.example.ui;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class TelegramKeyboard {

    public static ReplyKeyboardMarkup getMainMenu() {
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow locationRow = new KeyboardRow();
        KeyboardButton locationButton = new KeyboardButton("Погода по геолокации");
        locationButton.setRequestLocation(true);
        locationRow.add(locationButton);

        KeyboardRow mainRow = new KeyboardRow();
        mainRow.add(new KeyboardButton("/start"));
        mainRow.add(new KeyboardButton("/stats"));
        mainRow.add(new KeyboardButton("/forecast"));
        mainRow.add(new KeyboardButton("/help"));

        KeyboardRow exitRow = new KeyboardRow();
        exitRow.add(new KeyboardButton("/exit"));

        keyboardRows.add(locationRow);
        keyboardRows.add(mainRow);
        keyboardRows.add(exitRow);

        return ReplyKeyboardMarkup.builder()
                .keyboard(keyboardRows)
                .resizeKeyboard(true)
                .build();
    }

    public static ReplyKeyboardMarkup getCityKeyboard() {
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Москва"));
        row1.add(new KeyboardButton("Санкт-Петербург"));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("Екатеринбург"));
        row2.add(new KeyboardButton("Казань"));

        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton("назад"));

        keyboardRows.add(row1);
        keyboardRows.add(row2);
        keyboardRows.add(row3);

        return ReplyKeyboardMarkup.builder()
                .keyboard(keyboardRows)
                .resizeKeyboard(true)
                .build();
    }

    public static ReplyKeyboardMarkup getDaysKeyboard() {
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("1"));
        row1.add(new KeyboardButton("2"));
        row1.add(new KeyboardButton("3"));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("назад"));

        keyboardRows.add(row1);
        keyboardRows.add(row2);

        return ReplyKeyboardMarkup.builder()
                .keyboard(keyboardRows)
                .resizeKeyboard(true)
                .build();
    }
}
