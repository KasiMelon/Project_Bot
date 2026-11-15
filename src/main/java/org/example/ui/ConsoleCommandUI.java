package org.example.ui;

public class ConsoleCommandUI {
    public static String ExitInfo(Long chatId) {
        return LanguageManager.getText(chatId,"button.exit" );
    }

    public static String WelcomeInfo(Long chatId) {
        return LanguageManager.getText(chatId, "button.welcome");
    }

    public static String HelpInfo(Long chatId) {
        return LanguageManager.getText(chatId, "button.help");
    }

    public static String InputCityAndDays(Long chatId) {
        return LanguageManager.getText(chatId,"button.input.city_and_days");
    }

    public static String InputCity(Long chatId) {
        return LanguageManager.getText(chatId, "button.input.city");
    }


    public static String IncorrectCommand(Long chatId) {
        return LanguageManager.getText(chatId, "button.incorrect.command");
    }

    public static String IncorrectCity(Long chatId) {
        return LanguageManager.getText(chatId, "button.incorrect.city");
    }

    public static String IncorrectDay(Long chatId) {
        return LanguageManager.getText(chatId, "button.incorrect.day");
    }

    public static String ErrorNetwork(Long chatId) {
        return LanguageManager.getText(chatId, "errornetwork");
    }

    public static String ErrorServer(Long chatId) {
        return LanguageManager.getText(chatId, "errorserver");
    }

    public static void CommandResult(WeatherUI weatherUI) {
        System.out.println(weatherUI);
    }
}
