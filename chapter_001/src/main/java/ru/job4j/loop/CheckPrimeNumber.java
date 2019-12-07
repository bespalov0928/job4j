package ru.job4j.loop;

public class CheckPrimeNumber {
    public static boolean check(int finish) {
        int count = 0;
        for (int i = 1; i <= finish; i++) {
            if ((finish % i) == 0) {
                count++;
            }
        }
        return count == 2 ? true : false;
    }
}
