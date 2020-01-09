package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int result = o1.length() - o2.length();
        int length = Math.min(o1.length(), o2.length());
        for (int i = 0; i < length; i++) {
            if (o1.charAt(i) != o2.charAt(i)) {
                result = o2.charAt(i) - o1.charAt(i);
                break;
            }
        }
        return result;
    }
}
