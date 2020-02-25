package ru.job4j.socket;

import java.util.Scanner;

public class SystemInput implements ClientInput {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String get() {
        return scanner.nextLine();
    }
}
