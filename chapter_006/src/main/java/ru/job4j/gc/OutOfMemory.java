package ru.job4j.gc;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class OutOfMemory {
    public static void main(String[] args) {
        List<SoftReference<Integer>> list = new ArrayList<>();
        while (true) {
            list.add(new SoftReference<>(0));
        }
    }
}
