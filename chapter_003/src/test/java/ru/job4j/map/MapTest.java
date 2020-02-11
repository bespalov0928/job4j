package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;

public class MapTest {

    @Test
    public void mapTest() {
        var map = new HashMap<User, Object>();
        GregorianCalendar date = new GregorianCalendar();
        User first = new User("Test", 1, date);
        User second = new User("Test", 1, date);
        map.put(first, new Object());
        map.put(second, new Object());
    }
}
