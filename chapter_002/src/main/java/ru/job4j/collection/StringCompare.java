package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        int length = Math.min(o1.length(), o2.length());
        int result = 0;

        for (int index = 0; index < length; index++) {
            int char1 = o1.charAt(index);
            int char2 = o2.charAt(index);
            if (char1 != char2) {
                result = char1 - char2;
                break;
            }
        }

        if (result == 0 && o1.length() != o2.length()) {
            result = o1.length() - o2.length();
        }

        return result;
    }
}
