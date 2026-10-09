package org.commands;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelpCommand implements CommandExecute {
    private static final Logger logger = LoggerFactory.getLogger(HelpCommand.class);

    @Override
    public boolean Match(String input) {
        return input.equalsIgnoreCase("/help");
    }

    @Override
    public void execute(Scanner scanner) {
        logger.info("Чат-бот успешно выполнил команду пользователя /help");
        System.out.println(
                "Функционал бота включает в себя несколько команд:\n1. /forecast - показ погоды на запрошенный день недели (предел - 1 неделя вперёд)\n2. /stats - осадки, температура и погодные условия в целом на момент запроса\n3. /help - показ существующих команд и их краткое описание\n4. /exit - завершение работы ");
    }
}