package ru.job4j.magnet.store;

import ru.job4j.magnet.entity.Entries;
import ru.job4j.magnet.entity.Entry;

import java.util.List;

public interface XmlStore {
    void store(List<Entry> entries);
}
