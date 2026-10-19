package org.example;

import org.commands.BotController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;



public class CommandBot implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;
    private final BotController botController;

    public CommandBot(String botToken) {
        this.telegramClient = new OkHttpTelegramClient(botToken);
        this.botController = new BotController();

    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long charId = update.getMessage().getChatId();

            String responseText = botController.handleUserChoice(messageText);

            SendMessage message = SendMessage.builder()
                    .chatId(charId)
                    .text(responseText)
                    .build();

            try {
                telegramClient.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
