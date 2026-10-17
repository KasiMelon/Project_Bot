package org.commands;

import java.util.Scanner;
/**
 * Интерфейс команд бота
 * Метод Match проверяет, вызвана ли именно эта команда пользователем
 * Метод execute является реализацией команды, вызванной пользователем
 */
public interface Command {
    boolean Match(String input);

    void execute(Scanner scanner);
}