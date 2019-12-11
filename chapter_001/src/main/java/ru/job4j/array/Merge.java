package ru.job4j.array;

import java.util.Arrays;

public class Merge {
    public int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length || j < right.length) {
            int currentLeft = 0;
            int currentRight = 0;

            if (i < left.length) {
                currentLeft = left[i];
            }

            if (j < right.length) {
                currentRight = right[j];
            }

            if (currentLeft == currentRight) {
                rsl[k++] = currentLeft;
                rsl[k++] = currentRight;
                i++;
                j++;
            } else if (currentLeft == 0 || currentRight < currentLeft) {
                rsl[k++] = currentRight;
                j++;
            } else {
                rsl[k++] = currentLeft;
                i++;
            }
        }

        return rsl;
    }

    public static void main(String[] args) {
        Merge process = new Merge();
        int[] rsl = process.merge(
                new int[]{1, 3, 5},
                new int[]{2, 4}
        );
        System.out.println(Arrays.toString(rsl));
    }
}
