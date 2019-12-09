package ru.job4j.converter;

public class Converter {
    public static final int EQUIVALENT_OF_ONE_DOLLAR_TO_RUBLE = 60;
    public static final int EQUIVALENT_OF_ONE_EURO_TO_RUBLE = 70;

    public static int rubleToEuro(int value) {
        return value / EQUIVALENT_OF_ONE_EURO_TO_RUBLE;
    }

    public static int rubleToDollar(int value) {
        return value / EQUIVALENT_OF_ONE_DOLLAR_TO_RUBLE;
    }

    public static int euroToRuble(int value) {
        return value * EQUIVALENT_OF_ONE_EURO_TO_RUBLE;
    }

    public static int dollarToRuble(int value) {
        return value * EQUIVALENT_OF_ONE_DOLLAR_TO_RUBLE;
    }

    public static void main(String[] args) {

        double expected = 2;
        double out = rubleToEuro(140);
        System.out.println("140 rubles are 2. Test result : " + assertResult(expected, out));
        expected = 3;
        out = rubleToDollar(180);
        System.out.println("180 rubles are 3 dollars. Test result : " + assertResult(expected, out));
        expected = 140;
        out = euroToRuble(2);
        System.out.println("2 euro are 140 ruble. Test result : " + assertResult(expected, out));
        expected = 180;
        out = dollarToRuble(3);
        System.out.println("3 dollars are 180 ruble. Test result : " + assertResult(expected, out));
    }

    private static boolean assertResult(double expected, double result) {
        return expected == result;
    }


}
