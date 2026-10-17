package org.model.env;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static EnvConfig instance;

    private final String ApiWeather;

    private EnvConfig() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        this.ApiWeather = dotenv.get("ApiWeather");
    }

    public static synchronized EnvConfig getInstance() {
        if (instance == null) {
            instance = new EnvConfig();
        }
        return instance;
    }

    public String getApiWeather() {
        return ApiWeather;
    }
}

