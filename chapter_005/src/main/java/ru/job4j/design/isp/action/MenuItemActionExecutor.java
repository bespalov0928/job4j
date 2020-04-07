package ru.job4j.design.isp.action;

import ru.job4j.design.isp.menu.item.ActionMenuItem;

public interface MenuItemActionExecutor {
    void execute(ActionMenuItem item);
}
