package org.ui;

import java.util.Scanner;

public abstract class ConsoleUI implements UserInterface {
    protected static final Scanner scanner = new Scanner(System.in);
    public String userInput(){
        return scanner.nextLine();
    }
}
