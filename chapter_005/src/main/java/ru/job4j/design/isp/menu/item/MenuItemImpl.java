package ru.job4j.design.isp.menu.item;

import ru.job4j.design.isp.action.Action;

import java.util.Objects;

public class MenuItemImpl implements LevelMenuItem, ActionMenuItem {
    private int level;
    private Action action;
    private final String title;

    public MenuItemImpl(String title) {
        this.title = title;
    }

    public MenuItemImpl(String title, Action action) {
        this.title = title;
        this.action = action;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuItemImpl menuItem = (MenuItemImpl) o;
        return level == menuItem.level && title.equals(menuItem.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, title);
    }

    @Override
    public void executeAction() {
        if (action != null) {
            action.execute();
        }
    }
}
