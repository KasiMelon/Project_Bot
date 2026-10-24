package org.service.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ui.ConsoleCommandUI;

public class ExitCommand implements CommandExecute {
    private static final Logger logger = LoggerFactory.getLogger(ExitCommand.class);

    @Override
    public boolean match(String input) {
        return input.equalsIgnoreCase("/exit");
    }

    @Override
    public String execute(String arguments) {
        logger.info("Чат-бот успешно выполнил все команды пользователя");
        return ConsoleCommandUI.ExitInfo();
    }
}