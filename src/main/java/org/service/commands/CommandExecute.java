package org.service.commands;


public interface CommandExecute {
    boolean Match(String input);

    String execute(String arguments);
}