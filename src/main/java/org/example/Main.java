package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в чат бот прогноза погоды");
        System.out.print("Для старта чат бота введите команду /start: ");
        String start = scanner.nextLine();
        while (true) {
            if (start.equals("/start")) {
                System.out.println("Функционал данного чат бота: /forecast, /stats, /help, /exit ");
                String userChoice = scanner.nextLine();
                switch (userChoice) {
                    case "/forecast":
                        ForecastCommand forecast = new ForecastCommand();
                        forecast.execute(scanner);
                        break;
                    case "/stats":
                        StatsCommand stats = new StatsCommand();
                        stats.execute(scanner);
                        break;
                    case "/help":
                        System.out.println(
                                "Функционал бота включает в себя несколько команд:\n1. /forecast - показ погоды на запрошенный день недели (предел - 1 неделя вперёд)\n2. /stats - осадки, температура и погодные условия в целом на момент запроса\n3. /help - показ существующих команд и их краткое описание\n4. /exit - завершение работы ");
                        break;
                    case "/exit":
                        System.out.println("Спасибо что воспользовались нашим чат ботом. До свидание");
                        return;
                    default:
                        System.out.println("Вы ввели некорректную команду. Исправьте её и попробуйте снова.");
                }
            } else {
                System.out.println("Введённая команда не распознана");
            }
        }
    }
}