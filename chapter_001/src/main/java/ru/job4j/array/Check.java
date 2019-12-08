package ru.job4j.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int index = 0, standardIndex = 0; index < data.length; index++) {
            if (data[index] != data[standardIndex]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
