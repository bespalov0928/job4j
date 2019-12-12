package ru.job4j.io;

import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean run = true;
        int matches = 11;
        int currentPlayer = 2;

        System.out.println("Игра 11");
        while (matches > 0) {
            System.out.println("Осталось спичек:");
            for (int i = 0; i < matches; i++) {
                System.out.print("|");
            }
            System.out.println();
            currentPlayer = choosePlayer(currentPlayer);
            System.out.println(String.format("Игрок %d возьмите со стола от 1 до 3 спичек...", currentPlayer));
            int select = Integer.parseInt(input.nextLine());
            if (select < 1 || select > 3) {
                System.out.println("Введено не верное значение... Введите цифру от 1 до 3");
                currentPlayer = choosePlayer(currentPlayer);
            } else {
                matches -= select;
            }
        }
        System.out.println(String.format("Игрок %d победил!", currentPlayer));
    }

    public static int choosePlayer(int currentPlayer) {
        if (currentPlayer == 2) {
            currentPlayer = 1;
        } else {
            currentPlayer = 2;
        }
        return currentPlayer;
    }
}
