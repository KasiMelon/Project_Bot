package org.commands;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExitCommand implements CommandExecute {
    private static final Logger logger = LoggerFactory.getLogger(ExitCommand.class);

    @Override
    public boolean Match(String input) {
        return input.equalsIgnoreCase("/exit");
    }

    @Override
    public void execute(Scanner scanner) {
        logger.info("Чат-бот успешно выполнил все команды пользователя");
        System.out.println("Спасибо что воспользовались нашим чат ботом. До свидание");
    }
}