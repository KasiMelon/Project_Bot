package org.service.commands;

import org.ui.ConsoleCommandUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс ExitCommand, реализующуий команду выхода из программы и завершения работы бота
 */
public class ExitCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(ExitCommand.class);

    @Override
    public boolean match(String input) {
        return input.equalsIgnoreCase("/exit");
    }

    @Override
    public void execute() {
        logger.info("Чат-бот успешно выполнил все команды пользователя");
        ConsoleCommandUI.ExitInfo();
    }
}