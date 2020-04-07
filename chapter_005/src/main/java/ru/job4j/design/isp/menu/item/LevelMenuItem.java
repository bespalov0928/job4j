package ru.job4j.design.isp.menu.item;

public interface LevelMenuItem extends MenuItem {
    int getLevel();

    void setLevel(int level);
}
