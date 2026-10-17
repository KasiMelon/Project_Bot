package org.commands;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * данный класс принимает ввод пользователя и решает какое действие нужно выполнить
 */
public class BotController {
    private static final Logger logger = LoggerFactory.getLogger(BotController.class);
    private final List<CommandExecute> commands = List.of(
            new ForecastCommand(),
            new StatsCommand(),
            new HelpCommand(),
            new ExitCommand(),
            new StartCommand()
    );

    public String handleUserChoice(String userText) {
        String[] parts = userText.trim().split("\\s+", 2);//!!!!!!
        String commandName = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        for (CommandExecute command : commands) {
            if (command.Match(commandName)) {
                return command.execute(arguments);
            }
        }
        logger.warn("Команда вне диапазона: {}", commandName);
        return "Вы ввели неккоректную команду. Введите /help для списка команд.";
    }
}
