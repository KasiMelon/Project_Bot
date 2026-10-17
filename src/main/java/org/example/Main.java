package org.example;

import java.util.Scanner;

import io.github.cdimascio.dotenv.Dotenv;
import org.commands.BotController;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String apiBot = dotenv.get("TELEGRAM_BOT_TOKEN");

        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {

            botsApplication.registerBot(apiBot, new CommandBot(apiBot));
            System.out.println("Бот успешно заработал");

            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}