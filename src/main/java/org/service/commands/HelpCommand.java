package org.service.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * класс отвечает за обработку команды /help
 * метод Match сравнивает введёный текст с /help
 * метод execute выводит полезную информацию для пользователя
 */
public class HelpCommand implements CommandExecute {
    private static final Logger logger = LoggerFactory.getLogger(HelpCommand.class);

    @Override
    public boolean Match(String input) {
        return input.equalsIgnoreCase("/help");
    }

    @Override
    public String execute(String arguments) {
        logger.info("Чат-бот успешно выполнил команду пользователя /help");
        return "Функционал бота включает в себя несколько команд:\n" +
                "1. /forecast [город] [дни]- показ погоды на запрошенный день недели (предел - 1 неделя вперёд) (например: /forecast Москва 3)\n" +
                "2. /stats [город] - осадки, температура и погодные условия в целом на момент запроса\n" +
                "3. /help - показ существующих команд и их краткое описание\n" +
                "4. /exit - завершение работы ";
    }
}