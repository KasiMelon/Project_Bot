package org.model.env;

public class EnvConnect {
    public void connect() {
        String url1 = EnvConfig.getInstance().getApiWeather();
        String url2 = EnvConfig.getInstance().getApiTelegram();
    }
}
