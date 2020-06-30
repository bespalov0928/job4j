package ru.job4j.concurrent.nonblockingcache;

public class Base {
    final int id;
    int version;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }
}
