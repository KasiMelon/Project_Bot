package org.commands;

import java.util.Scanner;

public class ExitCommand implements CommandExecute {
	@Override
	public boolean Match(String input) {
		return input.equalsIgnoreCase("/exit");
	}

	@Override
	public void execute(Scanner scanner) {
		System.out.println("Спасибо что воспользовались нашим чат ботом. До свидание");
	}
}