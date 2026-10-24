package org.example;

import org.model.env.EnvConfig;
import org.model.env.EnvConnect;
import org.service.telegram.TelegramBot;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        EnvConfig config = EnvConfig.getInstance();
        EnvConnect db = new EnvConnect();
        db.connect();
        String apiBot = EnvConfig.getInstance().getApiTelegram();
        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {

            botsApplication.registerBot(apiBot, new TelegramBot(apiBot));
            logger.info("Бот успешно заработал!");

            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}