package ru.job4j.collection;

import java.util.HashSet;
import java.util.Set;

public class UsageHashSet {

    public static void main(String[] args) {
        Set<String> autos = Set.of("Lada", "BMW", "Volvo", "Toyota");
        for (String el : autos) {
            System.out.println(el);
        }
    }
}
