package ru.job4j.design.srp.store;

import ru.job4j.design.srp.entity.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    void add(Employee employee);

    List<Employee> findBy(Predicate<Employee> filter);
}
