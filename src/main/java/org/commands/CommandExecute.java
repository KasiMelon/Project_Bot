package org.commands;


public interface CommandExecute {
    boolean Match(String input);

    String execute(String arguments);
}