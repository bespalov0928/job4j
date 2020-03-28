package ru.job4j.design.srp.store;

import ru.job4j.design.srp.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListStore implements Store {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }
}
