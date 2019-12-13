package ru.job4j.poly;

public class Bus implements Vehicle {
    int passengers = 0;
    double fuel = 1.0;

    @Override
    public void ride() {
        System.out.println("Begin to ride");
    }

    @Override
    public void passengers(int count) {
        this.passengers = count;
    }

    @Override
    public double fuel() {
        return this.fuel;
    }
}
