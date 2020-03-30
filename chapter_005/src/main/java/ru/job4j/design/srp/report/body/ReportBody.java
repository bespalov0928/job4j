package ru.job4j.design.srp.report.body;

import ru.job4j.design.srp.entity.Employee;

import java.util.List;

public interface ReportBody {
    String generate(List<Employee> employees);
}
