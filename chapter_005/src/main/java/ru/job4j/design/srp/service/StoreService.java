package ru.job4j.design.srp.service;

import ru.job4j.design.srp.entity.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface StoreService {
    void add(Employee employee);

    List<Employee> findBy(Predicate<Employee> filter);

    List<Employee> findBy(Predicate<Employee> filter, Comparator<Employee> comparator);
}
