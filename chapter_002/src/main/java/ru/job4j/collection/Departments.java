package ru.job4j.collection;

import java.util.*;

public class Departments {
    public static List<String> fillGaps(List<String> departments) {
        Set<String> tmp = new LinkedHashSet<>();
        for (String value : departments) {
            StringBuilder start = new StringBuilder();
            for (String el : value.split("/")) {
                if (start.length() > 0) {
                    start.append("/");
                }
                start.append(el);
                tmp.add(start.toString());
            }
        }
        return new ArrayList<>(tmp);
    }

    public static void sortAsc(List<String> departments) {
        Collections.sort(departments);
    }

    public static void sortDesc(List<String> departments) {
        departments.sort(new DepartmentsDescComparator());
    }
}
