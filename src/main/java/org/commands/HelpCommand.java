package org.commands;

import java.util.Scanner;

public class HelpCommand implements CommandExecute {
	@Override
	public boolean Match(String input) {
		return input.equalsIgnoreCase("/help");
	}

	@Override
	public void execute(Scanner scanner) {
		System.out.println(
				"Функционал бота включает в себя несколько команд:\n1. /forecast - показ погоды на запрошенный день недели (предел - 1 неделя вперёд)\n2. /stats - осадки, температура и погодные условия в целом на момент запроса\n3. /help - показ существующих команд и их краткое описание\n4. /exit - завершение работы ");
	}
}