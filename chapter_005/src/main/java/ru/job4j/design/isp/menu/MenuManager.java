package ru.job4j.design.isp.menu;

import ru.job4j.design.isp.action.MenuItemActionExecutor;
import ru.job4j.design.isp.input.Input;
import ru.job4j.design.isp.menu.item.ActionMenuItem;
import ru.job4j.design.isp.service.MenuItemFinderService;
import ru.job4j.design.isp.status.ExitStatus;

public class MenuManager implements MenuItemActionExecutor {
    private final Menu menu;
    private final Input input;
    private final MenuItemFinderService<ActionMenuItem> finderService;
    private final ExitStatus exitStatus;

    public MenuManager(
            Menu menu,
            Input input,
            MenuItemFinderService<ActionMenuItem> finderService,
            ExitStatus exitStatus) {
        this.menu = menu;
        this.input = input;
        this.finderService = finderService;
        this.exitStatus = exitStatus;
    }

    public void run() {
        while (exitStatus.notExit()) {
            menu.display();
            var userInput = input.get();
            ActionMenuItem menuItem = finderService.findByIndex(Integer.parseInt(userInput));
            if (menuItem != null) {
                execute(menuItem);
            }
        }
    }

    @Override
    public void execute(ActionMenuItem item) {
        item.executeAction();
    }
}
