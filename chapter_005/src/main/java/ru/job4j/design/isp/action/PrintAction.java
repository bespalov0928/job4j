package ru.job4j.design.isp.action;

import ru.job4j.design.isp.action.Action;

import java.io.PrintStream;

public class PrintAction implements Action {
    private final String message;

    public PrintAction(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        System.out.println(message);
    }
}
