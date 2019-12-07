package ru.job4j.calculator;

/**
 * Calculator
 *
 * @author Dmitry Pratsun (pratsundv@gmail.com)
 */
public class Calculator {
    /**
     * Method add. Print sum of two numbers to console
     * @param first - number
     * @param second - number
     */
    public static void add(int first, int second) {
        int result =  first + second;
        System.out.println(first + " + " + second + " = " + result);
    }

    /**
     * Method div. Print result of division of two numbers to console
     * @param first - dividend number
     * @param second - divider number
     */
    public static void div(int first, int second) {
        int result =  first / second;
        System.out.println(first + " / " + second + " = " + result);
    }

    /**
     * Method multiply. Print result of multiply of two numbers to console
     * @param first - multiplicand
     * @param second - multiplier
     */
    public static void multiply(int first, int second) {
        int result =  first * second;
        System.out.println(first + " * " + second + " = " + result);
    }

    /**
     * Method subtract. Print result of subtraction of two numbers to console
     * @param first - number
     * @param second - number
     */
    public static void subtract(int first, int second) {
        int result =  first - second;
        System.out.println(first + " - " + second + " = " + result);
    }

    /**
     * Method main
     * @param args - args
     */
    public static void main(String[] args) {
        add(1, 1);
        div(4, 2);
        multiply(2, 1);
        subtract(10, 5);
    }
}
