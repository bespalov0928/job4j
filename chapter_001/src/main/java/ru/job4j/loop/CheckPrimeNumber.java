package ru.job4j.loop;

public class CheckPrimeNumber {
    public static boolean check(int finish) {
        boolean prime = true;
        for (int i = 2; i < finish; i++) {
            if ((finish % i) == 0) {
                prime = false;
                break;
            }
        }
        return prime;
    }
}
