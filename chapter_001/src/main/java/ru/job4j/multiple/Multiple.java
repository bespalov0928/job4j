package ru.job4j.multiple;

/**
 * Created by prats on 12/2/19.
 */
public class Multiple {
    public static void main(String[] args) {
        final int multiplier = 1;

        for (int i = 1; i < 10; i++) {
            System.out.println(String.format("%d * %d = %d", multiplier, i, (multiplier * i)));
        }
    }
}
