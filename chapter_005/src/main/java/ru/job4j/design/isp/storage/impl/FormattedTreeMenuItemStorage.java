package ru.job4j.design.isp.storage.impl;

import ru.job4j.design.isp.menu.Menu;
import ru.job4j.design.isp.menu.item.*;
import ru.job4j.design.isp.storage.TreeMenuItemStorage;
import ru.job4j.design.isp.storage.impl.TreeMenuItemStorageImpl;

import java.util.Iterator;

public class FormattedTreeMenuItemStorage extends TreeMenuItemStorageImpl {
    private final TreeMenuItemStorage<LevelMenuItem> storage;

    public FormattedTreeMenuItemStorage(TreeMenuItemStorage<LevelMenuItem> storage) {
        this.storage = storage;
    }

    @Override
    public Iterator<LevelMenuItem> iterator() {
        return new Iterator<>() {
            private final Iterator<LevelMenuItem> it = storage.iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public LevelMenuItem next() {
                return new FormattedMenuItem(it.next());
            }
        };
    }
}
