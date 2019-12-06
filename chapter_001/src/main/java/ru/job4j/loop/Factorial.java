package ru.job4j.loop;

public class Factorial {
    public int calc(int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= (i + 1);
        }
        return result;
    }
}
