package org.ui;

public class ConsoleHandlerUI extends ConsoleUI{
    public static void userOutput() {
        System.out.println("Функционал данного чат бота: /forecast, /stats, /help, /exit");
    }
    public static void incorrectOutput(){
        System.out.println("Вы ввели некорректную команду. Запустите чат-бот снова и введите /start");
    }
    public static void userInfo(){
        System.out.println("Добро пожаловать в чат бот прогноза погоды");
        System.out.println("Для старта чат бота введите команду /start: ");
    }
}
