package org.example.ui;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class LanguageManager {

    private static Map<Long, Locale> userLocales = new HashMap<>();

    public static void setUserLanguage(Long chatId, String languageCode) {
        Locale newLocale = new Locale(languageCode);
        userLocales.put(chatId, newLocale);
    }

    public static String getText(Long chatId, String key) {
        Locale userLocale = userLocales.getOrDefault(chatId, new Locale("ru"));
        ResourceBundle bundle = ResourceBundle.getBundle("message", userLocale);
        return bundle.getString(key);
    }

    public static String getLangCode(Long chatId) {

        Locale userLocale = userLocales.getOrDefault(chatId, new Locale("ru"));
        return userLocale.getLanguage();
    }


}
