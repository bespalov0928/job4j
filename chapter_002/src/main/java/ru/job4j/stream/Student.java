package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Student {
    private String name;
    private Set<String> units;

    public Student(String name, Set<String> units) {
        this.name = name;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public Set<String> getUnits() {
        return units;
    }

    public static List<Holder> studentToHolderList(Student student) {
        List<Holder> holders = new ArrayList<>();
        student.units.forEach(u -> holders.add(new Holder(student.name, u)));
        return holders;
    }
}
