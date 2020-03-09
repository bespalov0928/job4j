package ru.job4j.tracker;

import ru.job4j.tracker.tracker.ITracker;

public interface UserAction {
    String name();

    boolean execute(Input input, ITracker tracker);
}
