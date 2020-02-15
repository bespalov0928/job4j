package ru.job4j.chat;

import java.util.Scanner;

public class ConsoleMessages implements Messages {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String getMessage() {
        return scanner.nextLine();
    }
}
