package org.ui;

public class ConsoleCommandUI {
    public static String ExitInfo() {
        return "Спасибо что воспользовались нашим чат ботом. До свидание";
    }

    public static String WelcomeInfo() {
        return "Добро пожаловать в чат-бот прогноза погоды! Бот может выполнять такие команды: /stats /forecast /help /exit";
    }

    public static String HelpInfo() {
        return "Функционал бота включает в себя несколько команд:\n" +
                "1. /forecast [город] [дни]- показ погоды на запрошенный день недели (предел - 1 неделя вперёд) (например: /forecast Москва 3)\n" +
                "2. /stats [город] - осадки, температура и погодные условия в целом на момент запроса\n" +
                "3. /help - показ существующих команд и их краткое описание\n" +
                "4. /exit - завершение работы ";
    }

    public static String InputCityAndDays() {
        return "Укажите город и количество дней. Пример: /forecast Москва 3";
    }

    public static String InputCity() {
        return "Пожалуйста укажите город. Пример: /stats Москва";
    }


    public static String IncorrectCommand() {
        return "Вы ввели неккоректную команду. Введите /help для списка команд.";
    }

    public static String IncorrectCity() {
        return "Вы ввели некорректное название города, перепроверьте его и введите снова";
    }

    public static String IncorrectDay() {
        return "Прогноз можно запросить только в диапазоне от 0 до 7 дней. Введите диапазон снова.";
    }

    public static String ErrorNetwork() {
        return "Возникла ошибка в процессе установления связи с сервером погоды.";
    }

    public static String ErrorServer() {
        return "Возникла ошибка на сервере погоды (Код: ";
    }

    public static void CommandResult(WriterUI writerUI) {
        System.out.println(writerUI);
    }
}
