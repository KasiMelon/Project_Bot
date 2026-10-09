package org.example;

import java.util.Scanner;

import org.commands.BotController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в чат бот прогноза погоды");
        System.out.print("Для старта чат бота введите команду /start: ");
        String userChoice = scanner.nextLine();
        BotController botController = new BotController();
        while (true) {
            if (userChoice.equals("/start")) {
                do {
                    System.out.println("Функционал данного чат бота: /forecast, /stats, /help, /exit ");
                    userChoice = scanner.nextLine();
                    botController.handleUserChoice(userChoice, scanner);
                } while (!userChoice.equals("/exit"));
                return;
            } else {
                System.out.println("Введённая команда не распознана, попробуйте ещё раз:");
                userChoice = scanner.nextLine();
            }
        }
    }
}