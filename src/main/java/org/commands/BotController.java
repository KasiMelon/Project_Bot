package org.commands;

import java.util.List;
import java.util.Scanner;

public class BotController {
    private final List<CommandExecute> commands = List.of(
            new ForecastCommand(),
            new StatsCommand(),
            new HelpCommand(),
            new ExitCommand());

    public void handleUserChoice(String userChoice, Scanner scanner) {
        boolean commandFound = false;
        for (CommandExecute command : commands) {
            if (command.Match(userChoice)) {
                command.execute(scanner);
                commandFound = true;
                break;
            }
        }
        if (!commandFound) {
            System.out.println("Вы ввели некорректную команду. Исправьте её и попробуйте снова.");
        }
    }
}
