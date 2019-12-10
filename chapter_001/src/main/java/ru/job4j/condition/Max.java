package ru.job4j.condition;

public class Max {
    public int max(int first, int second) {
        return Math.max(first, second);
    }

    public int max(int first, int second, int third) {
        int result = this.max(first, second);
        return this.max(result, third);
    }

    public int max(int first, int second, int third, int fourth) {
        int result = this.max(first, second, third);
        return this.max(result, fourth);
    }
}
