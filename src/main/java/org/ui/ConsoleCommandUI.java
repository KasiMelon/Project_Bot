package org.ui;

public class ConsoleCommandUI extends ConsoleUI {
    public static void HelpInfo() {
        System.out.println(
                "Функционал бота включает в себя несколько команд:\n1. /forecast - показ погоды на запрошенный день недели (предел - 1 неделя вперёд)\n2. /stats - осадки, температура и погодные условия в целом на момент запроса\n3. /help - показ существующих команд и их краткое описание\n4. /exit - завершение работы ");
    }

    public static void ExitInfo() {
        System.out.println("Спасибо что воспользовались нашим чат ботом. До свидание");
    }

    public String userTrimInput() {
        return scanner.nextLine().trim();
    }

    public static void InputCity() {
        System.out.println("Введите город: ");
    }

    public static void InputDay() {
        System.out.print("На сколько дней нужен будет прогноз? (0 - на сегодня, максимум на 7 дней): ");
    }

    public static void IncorrectNumber() {
        System.out.println("Необходимо ввести числовое значение. Попробуйте ещё раз.\n");
    }

    public static void IncorrectCity() {
        System.out.println("Вы ввели некорректное название города, перепроверьте его и введите снова");
    }

    public static void IncorrectDay() {
        System.out.println("Прогноз можно запросить только в диапазоне от 0 до 7 дней. Введите диапазон снова.\n");
    }
    public static void Error() {
        System.out.println("Возникла ошибка");
    }
    public static void NetworkError() {
        System.out.println("Возникла ошибка при установлении связи");
    }
    public static void CommandResult(WriterUI writerUI){
        System.out.println(writerUI);
    }
}
