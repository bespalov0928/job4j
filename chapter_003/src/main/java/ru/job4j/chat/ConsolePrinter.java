package ru.job4j.chat;

public class ConsolePrinter implements Printer {
    @Override
    public void print(String output) {
        System.out.println(output);
    }
}
