package ru.job4j.array;

public class Defragment {
    public static String[] compress(String[] array) {
        int moveCount = 1;
        for (int index = 0; index < array.length; index++) {
            String cell = array[index];
            if (cell == null) {
                int nullIndex = index;
                while (nullIndex < array.length - moveCount) {
                    cell = array[++nullIndex];
                    array[nullIndex - 1] = cell;
                    array[nullIndex] = null;
                }
                moveCount++;
            }
            System.out.print(array[index] + " ");
        }
        return array;
    }

    public static void main(String[] args) {
        String[] input = {"I", null, "wanna", null, "be", null, "compressed"};
        String[] compressed = compress(input);
        System.out.println();
        for (String s : compressed) {
            System.out.print(s + " ");
        }
    }
}
