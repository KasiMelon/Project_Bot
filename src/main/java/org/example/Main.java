package org.example;

import org.model.env.EnvConfig;
import org.model.env.EnvConnect;
import org.service.handler.Handler;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.ui.ConsoleHandlerUI;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * основная часть кода, отвечающая за взаимодействие с пользователем
     *
     * @param args
     */
    public static void main(String[] args) {
        EnvConfig config = EnvConfig.getInstance();
        EnvConnect db = new EnvConnect();
        db.connect();
        ConsoleHandlerUI.userInfo();
        ConsoleHandlerUI ui = new ConsoleHandlerUI();
        String userChoice = ui.userInput();
        Handler.handle(userChoice);
    }
}