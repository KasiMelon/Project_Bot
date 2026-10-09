package org.commands;

import java.util.Scanner;

public interface CommandExecute {
	boolean Match(String input);

	void execute(Scanner scanner);
}