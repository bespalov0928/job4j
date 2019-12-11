package ru.job4j.oop.inheritance;

public class Programmer extends Engineer {
    String majority;

    public Programmer(String profession, String majority) {
        super(profession);
        this.majority = majority;
    }

    public String getMajority() {
        return majority;
    }

    public Program makeProgram(Description description) {
        return null;
    }
}
