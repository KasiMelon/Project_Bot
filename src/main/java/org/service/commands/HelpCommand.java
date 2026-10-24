package org.service.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ui.ConsoleCommandUI;

public class HelpCommand implements CommandExecute {
    private static final Logger logger = LoggerFactory.getLogger(HelpCommand.class);

    @Override
    public boolean match(String input) {
        return input.equalsIgnoreCase("/help");
    }

    @Override
    public String execute(String arguments) {
        logger.info("Чат-бот успешно выполнил команду пользователя /help");
        return ConsoleCommandUI.HelpInfo();
    }
}