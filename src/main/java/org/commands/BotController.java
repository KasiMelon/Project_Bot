package org.commands;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Принимает ввод пользователя и определяет, какую команду нужно выполнить
 */
public class BotController {
    private static final Logger logger = LoggerFactory.getLogger(BotController.class);
    private final List<Command> commands = List.of(
            new ForecastCommand(),
            new StatsCommand(),
            new HelpCommand(),
            new ExitCommand());

    public void handleUserChoice(String userChoice, Scanner scanner) {
        boolean commandFound = false;
        for (Command command : commands) {
            if (command.Match(userChoice)) {
                command.execute(scanner);
                commandFound = true;
                break;
            }
        }
        if (!commandFound) {
            logger.warn("Команда вне диапазона: {}", userChoice);
            System.out.println("Вы ввели некорректную команду. Исправьте её и попробуйте снова.");
        }
    }
}
