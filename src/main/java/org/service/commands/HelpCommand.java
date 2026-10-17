package org.service.commands;

import org.ui.ConsoleCommandUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HelpCommand реализует вывод руководства по пользованию ботом для пользователя.
 */
public class HelpCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(HelpCommand.class);

    @Override
    public boolean match(String input) {
        return input.equalsIgnoreCase("/help");
    }

    @Override
    public void execute() {
        logger.info("Чат-бот успешно выполнил команду пользователя /help");
        ConsoleCommandUI.HelpInfo();
    }
}