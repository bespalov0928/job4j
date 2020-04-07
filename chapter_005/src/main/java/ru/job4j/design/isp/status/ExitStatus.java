package ru.job4j.design.isp.status;

public class ExitStatus {
    private boolean exit = false;

    public boolean notExit() {
        return !exit;
    }

    public void exit() {
        this.exit = true;
    }
}
