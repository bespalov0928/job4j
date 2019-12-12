package ru.job4j.io;

import java.util.Random;
import java.util.Scanner;

public class MagicBall {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Я великий Оракул. Что ты хочешь узнать?");
        input.nextLine();
        int number = new Random().nextInt(3);
        String answer;
        switch (number) {
            case 0:
                answer = "Да";
                break;
            case 1:
                answer = "Нет";
                break;
            default:
                answer = "Может быть";
        }
        System.out.println(answer);
    }
}
