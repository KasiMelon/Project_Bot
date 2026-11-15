package org.example.service.telegram;

import org.example.service.commands.BotController;
import org.example.ui.TelegramKeyboard;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TelegramBot implements LongPollingSingleThreadUpdateConsumer {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    private final TelegramClient telegramClient;
    private final BotController botController;

    private final Map<Long, String> userStates = new ConcurrentHashMap<>();
    private final Map<Long, String> tempForecastDays = new ConcurrentHashMap<>();

    public TelegramBot(String botToken) {
        this.telegramClient = new OkHttpTelegramClient(botToken);
        this.botController = new BotController();

    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage()) {

            long chatId = update.getMessage().getChatId();
            String responseText = null;

            if (update.getMessage().hasText()) {

                String messageText = update.getMessage().getText();

                if (messageText.equals("назад")) {
                    userStates.remove(chatId);
                    tempForecastDays.remove(chatId);
                    messageText = "/start";
                } else if ("WAITING_CITY_FOR_STATS".equals(userStates.get(chatId))) {
                    messageText = "/stats " + messageText;
                    userStates.remove(chatId);
                } else if ("WAITING_DAYS_FOR_FORECAST".equals(userStates.get(chatId))) {
                    tempForecastDays.put(chatId, messageText);
                    userStates.put(chatId, "WAITING_CITY_FOR_FORECAST");

                    sendDirectMessage(chatId, "Теперь выберите город:", TelegramKeyboard.getCityKeyboard());
                    return;

                } else if ("WAITING_CITY_FOR_FORECAST".equals(userStates.get(chatId))) {
                    String days = tempForecastDays.getOrDefault(chatId, "1");
                    messageText = "/forecast " + messageText + " " + days;

                    userStates.remove(chatId);
                    tempForecastDays.remove(chatId);
                }

                responseText = botController.handleUserChoice(messageText);

            } else if (update.getMessage().hasLocation()) {

                userStates.remove(chatId);
                double lat = update.getMessage().getLocation().getLatitude();
                double lon = update.getMessage().getLocation().getLongitude();

                String coordinates = lat + "," + lon;
                logger.info("получены координаты");
                responseText = botController.handleUserChoice("/stats " + coordinates);
            }

            if (responseText != null) {

                SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder()
                        .chatId(chatId)
                        .text(responseText);

                if (responseText.equals(org.example.ui.ConsoleCommandUI.InputCity())) {

                    messageBuilder.replyMarkup(TelegramKeyboard.getCityKeyboard());
                    userStates.put(chatId, "WAITING_CITY_FOR_STATS");

                } else if (responseText.equals(org.example.ui.ConsoleCommandUI.InputCityAndDays())) {

                    messageBuilder.text("Выбери количество дней для прогноза:");
                    messageBuilder.replyMarkup(TelegramKeyboard.getDaysKeyboard());
                    userStates.put(chatId, "WAITING_DAYS_FOR_FORECAST");

                } else {

                    messageBuilder.replyMarkup(TelegramKeyboard.getMainMenu());
                    
                }
                try {
                    telegramClient.execute(messageBuilder.build());
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendDirectMessage(long chatId, String text, org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard keyboard) {
        try {
            telegramClient.execute(SendMessage.builder()
                    .chatId(chatId)
                    .text(text)
                    .replyMarkup(keyboard)
                    .build());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
