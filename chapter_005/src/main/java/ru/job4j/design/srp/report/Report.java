package ru.job4j.design.srp.report;

import ru.job4j.design.srp.entity.Employee;

import java.util.List;

public interface Report {
    String generate(List<Employee> employees);
}
