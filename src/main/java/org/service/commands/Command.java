package org.service.commands;

/**
 * Интерфейс команд бота
 * Метод match проверяет, вызвана ли именно эта команда пользователем
 * Метод execute является реализацией команды, вызванной пользователем
 */
public interface Command {
    boolean match(String input);

    void execute();
}