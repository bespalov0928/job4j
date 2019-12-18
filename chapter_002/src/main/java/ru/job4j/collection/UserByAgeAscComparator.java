package ru.job4j.collection;

import java.util.Comparator;

public class UserByAgeAscComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}
