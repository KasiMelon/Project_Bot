package org.service.commands;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.ui.ConsoleCommandUI;

public class StartCommand implements CommandExecute {
    private static final Logger logger = LoggerFactory.getLogger(StartCommand.class);

    @Override
    public boolean match(String input) {

        return input.equalsIgnoreCase("/start");
    }

    @Override
    public String execute(String arguments) {
        logger.info("Бот начал работу");
        return ConsoleCommandUI.WelcomeInfo();
    }
}
