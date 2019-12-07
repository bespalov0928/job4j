package ru.job4j.calculator;

public class Fit {
    public static final double MAN_HEIGHT_DELTA = 100D;
    public static final double WOMAN_HEIGHT_DELTA = 110D;
    public static final double MULTIPLY_COEFFICIENT = 1.15;

    public static double manWeight(double height) {
        return idealWeightCalculation(height, MAN_HEIGHT_DELTA);
    }

    public static double womanWeight(double height) {
        return idealWeightCalculation(height, WOMAN_HEIGHT_DELTA);
    }

    private static double idealWeightCalculation(double height, double heightDelta) {
        return (height - heightDelta) * MULTIPLY_COEFFICIENT;
    }

    public static void main(String[] args) {
        System.out.println("Man 178 is " + manWeight(178));
        System.out.println("Woman 165 is " + womanWeight(165));
    }
}
