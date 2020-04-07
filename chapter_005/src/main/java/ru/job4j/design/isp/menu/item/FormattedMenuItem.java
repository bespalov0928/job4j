package ru.job4j.design.isp.menu.item;

public class FormattedMenuItem extends MenuItemImpl {
    private final static String INDENT = "----";

    private final LevelMenuItem item;

    public FormattedMenuItem(LevelMenuItem item) {
        super(item.getTitle());
        this.item = item;
    }

    @Override
    public String getTitle() {
        return INDENT.repeat(item.getLevel()) + item.getTitle();
    }
}
