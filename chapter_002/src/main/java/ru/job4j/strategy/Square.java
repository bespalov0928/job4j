package ru.job4j.strategy;

public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder shape = new StringBuilder();
        shape.append("++++").append(System.lineSeparator());
        shape.append("+  +").append(System.lineSeparator());
        shape.append("+  +").append(System.lineSeparator());
        shape.append("++++");
        return shape.toString();
    }
}
