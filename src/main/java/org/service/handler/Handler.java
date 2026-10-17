package org.service.handler;

import org.ui.ConsoleHandlerUI;
import org.service.commands.BotController;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Handler {
    private static final Logger logger = LoggerFactory.getLogger(Handler.class);

    public static void handle(String userChoice) {
        BotController botController = new BotController();
        ConsoleHandlerUI ui = new ConsoleHandlerUI();
        while (true) {
            if (userChoice.equals("/start")) {
                do {
                    ConsoleHandlerUI.userOutput();
                    userChoice = ui.userInput();
                    botController.handleUserChoice(userChoice);
                } while (!userChoice.equalsIgnoreCase("/exit"));
                return;

            } else {
                logger.error("Команда на старт не распознана {}", userChoice);
                ConsoleHandlerUI.incorrectOutput();
                return;
            }
        }
    }
}
