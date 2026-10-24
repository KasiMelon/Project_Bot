package org.service.commands;


public interface CommandExecute {
    boolean match(String input);

    String execute(String arguments);
}