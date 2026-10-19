package org.example;

import org.model.env.EnvConfig;
import org.model.env.EnvConnect;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * основная часть кода, отвечающая за взаимодействие с пользователем
     * @param args
     */
    public static void main(String[] args) {
        EnvConfig config = EnvConfig.getInstance();
        EnvConnect db = new EnvConnect();
        db.connect();

        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {

            botsApplication.registerBot(apiBot, new CommandBot(apiBot));
            System.out.println("Бот успешно заработал");

            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}