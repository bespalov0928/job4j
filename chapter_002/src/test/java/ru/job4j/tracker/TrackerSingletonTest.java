package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.singleton.TrackerSingleClass;
import ru.job4j.singleton.TrackerSingleEager;
import ru.job4j.singleton.TrackerSingleEnum;
import ru.job4j.singleton.TrackerSingleLazy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TrackerSingletonTest {

    @Test
    public void testEnumSingleton() {
        TrackerSingleEnum firstTracker = TrackerSingleEnum.INSTANCE;
        TrackerSingleEnum secondTracker = TrackerSingleEnum.INSTANCE;
        assertEquals(firstTracker, secondTracker);
    }

    @Test
    public void testLazySingleton() {
        TrackerSingleLazy firstTracker = TrackerSingleLazy.getInstance();
        TrackerSingleLazy secondTracker = TrackerSingleLazy.getInstance();
        assertEquals(firstTracker, secondTracker);
    }

    @Test
    public void testEagerSingleton() {
        TrackerSingleEager firstTracker = TrackerSingleEager.getInstance();
        TrackerSingleEager secondTracker = TrackerSingleEager.getInstance();
        assertEquals(firstTracker, secondTracker);
    }

    @Test
    public void testClassSingleton() {
        TrackerSingleClass firstTracker = TrackerSingleClass.getInstance();
        TrackerSingleClass secondTracker = TrackerSingleClass.getInstance();
        assertEquals(firstTracker, secondTracker);
    }
}
