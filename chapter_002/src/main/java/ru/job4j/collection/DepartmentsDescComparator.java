package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] splitO1 = o1.split("/");
        String[] splitO2 = o2.split("/");
        int result = 0;
        for (int i = 0; i < splitO1.length && i < splitO2.length; i++) {
            result = splitO2[i].compareTo(splitO1[i]);
            if (result != 0) {
                break;
            }
        }
        return result;
    }
}
