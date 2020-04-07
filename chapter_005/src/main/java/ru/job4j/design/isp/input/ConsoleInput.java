package ru.job4j.design.isp.input;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String get() {
        return scanner.nextLine();
    }
}
