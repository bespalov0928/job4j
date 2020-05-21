package ru.job4j.gc;

public class StackOverflow {

    public static int recursion(int number) {
        return recursion(number + 1);
    }

    public static void main(String[] args) {
        StackOverflow.recursion(1);
    }
}
