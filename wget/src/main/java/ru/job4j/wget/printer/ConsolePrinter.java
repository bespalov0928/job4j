package ru.job4j.wget.printer;

import ru.job4j.wget.printer.Printer;

public class ConsolePrinter implements Printer {
    @Override
    public void print(String message) {
        System.out.print(message);
    }
}
