package ru.job4j.tracker;

import java.util.Comparator;

public class ItemCompareByNameDesc implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        String name1 = ((Item) o1).getName();
        String name2 = ((Item) o2).getName();
        int length = Math.min(name1.length(), name2.length());
        int result = 0;

        for (int index = 0; index < length; index++) {
            int char1 = name1.charAt(index);
            int char2 = name2.charAt(index);
            if (char1 != char2) {
                result = char2 - char1;
                break;
            }
        }

        if (result == 0 && name1.length() != name2.length()) {
            result = name2.length() - name1.length();
        }

        return result;
    }
}
