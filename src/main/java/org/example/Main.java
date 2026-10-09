package org.example;

import java.util.Scanner;

import org.commands.BotController;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в чат бот прогноза погоды");
        System.out.println("Для старта чат бота введите команду /start: ");
        String userChoice = scanner.nextLine();
        BotController botController = new BotController();
        while (true) {
            if (userChoice.equals("/start")) {
                do {
                    System.out.println("Функционал данного чат бота: /forecast, /stats, /help, /exit ");
                    userChoice = scanner.nextLine();
                    botController.handleUserChoice(userChoice, scanner);
                } while (!userChoice.toLowerCase().equals("/exit"));
                return;

            } else {
                logger.error("Команда на старт не распознана {}", userChoice);
                System.out.println("Вы ввели некорректную команду. Запустите чат-бот снова и введите /start");
                return;
            }
        }
    }
}