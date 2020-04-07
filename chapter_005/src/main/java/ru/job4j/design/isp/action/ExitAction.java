package ru.job4j.design.isp.action;

import ru.job4j.design.isp.status.ExitStatus;

public class ExitAction implements Action {
    private final ExitStatus exitStatus;

    public ExitAction(ExitStatus exitStatus) {
        this.exitStatus = exitStatus;
    }

    @Override
    public void execute() {
        exitStatus.exit();
    }
}
