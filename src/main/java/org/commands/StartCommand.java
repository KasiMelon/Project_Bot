package org.commands;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class StartCommand implements CommandExecute{
    private static final Logger logger = LoggerFactory.getLogger(StartCommand.class);

    @Override
    public boolean Match(String input) {

        return input.equalsIgnoreCase("/start");
    }

    @Override
    public String execute(String arguments) {
        logger.info("Бот начал работу");
        return "Добро пожаловать в чат-бот прогноза погоды! Бот может выполнять такие команды: /stats /forecast /help /exit";
    }
}
