package org.service.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * данный класс отвечает за обработку команды /exit
 * метод Match проверяет слвпадает ли введёный текст с /exit
 * метод execute выводит прощальное сообщение
 */
public class ExitCommand implements CommandExecute {
    private static final Logger logger = LoggerFactory.getLogger(ExitCommand.class);

    @Override
    public boolean Match(String input) {
        return input.equalsIgnoreCase("/exit");
    }

    @Override
    public String execute(String arguments) {
        logger.info("Чат-бот успешно выполнил все команды пользователя");
        return "Спасибо что воспользовались нашим чат ботом. До свидание";
    }
}