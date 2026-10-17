package org.model.env;

public class EnvConnect {
    public void connect() {
        String url = EnvConfig.getInstance().getApiWeather();
    }
}
