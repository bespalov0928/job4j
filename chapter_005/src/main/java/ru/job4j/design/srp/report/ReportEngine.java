package ru.job4j.design.srp.report;

import ru.job4j.design.srp.entity.Employee;
import ru.job4j.design.srp.service.StoreService;

import java.util.Comparator;
import java.util.function.Predicate;

public class ReportEngine {
    private StoreService storeService;
    private Report report;

    public ReportEngine(StoreService storeService, Report report) {
        this.storeService = storeService;
        this.report = report;
    }

    public String generate(Predicate<Employee> filter) {
        return report.generate(storeService.findBy(filter));
    }

    public String generate(Predicate<Employee> filter, Comparator<Employee> comparator) {
        return report.generate(storeService.findBy(filter, comparator));
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
